package CA.CAPS.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	static int pageSize = 5;
	
	@GetMapping("/lecturer/add")
	public String addLecturer(Model model) {
		Lecturer lecturer = new Lecturer();
		model.addAttribute("lecturer", lecturer);
		return "admin/admin-lecturer-form";
	}
	
	@RequestMapping("/lecturer/save")
	public String saveLecturer(@ModelAttribute("lecturer") @Valid Lecturer lecturer, BindingResult bindingResult) {
		
		if (adminService.isUserNameExist(lecturer)) {
			 bindingResult.rejectValue("userName", "error.userName", "Username exists.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/admin-lecturer-form";
		}
		
		adminService.saveLecturer(lecturer);
		return "forward:/admin/lecturer/list";
	}
	
	@GetMapping("/lecturer/edit/{id}")
	public String updateLecturer(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("lecturer", adminService.findLecturerById(id));
		return "admin/admin-lecturer-form";
	}
	
	@GetMapping("/lecturer/delete/{id}")
	public String deleteLecturer(@PathVariable("id") Integer id) {
		Lecturer lecturer = adminService.findLecturerById(id);
		adminService.removeLecturerFromCourses(lecturer);
		adminService.deleteLecturer(lecturer);
		return "forward:/admin/lecturer/list";
	}
	
	@RequestMapping("/lecturer/list")
	public String listLecturers(@RequestParam("page") Optional<Integer> page, Model model) {
		int requestPage = page.orElse(1);
		Pageable pageable = PageRequest.of(requestPage-1, pageSize, Sort.by("userName"));
		Page<Lecturer> adminPage = adminService.findLecturerPaginated(pageable);
		model.addAttribute("adminPage", adminPage);
		
        int totalPages = adminPage.getTotalPages();        
        if (totalPages > 0 ) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
		model.addAttribute("lecturers", adminService.listAllLecturers(pageable));
		return "admin/admin-view-lecturer";
	}
	
	@GetMapping("/course/add")
	public String addCourse(Model model) {
		
		Course course = new Course();
		model.addAttribute("course", course);
		model.addAttribute("lecturers", adminService.listAllLecturers());
		return "admin/admin-course-form";

	}
	
	@RequestMapping("/course/save")
	public String saveCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, Model model) {
		
		model.addAttribute("lecturers", adminService.listAllLecturers());
		
		if (adminService.isCourseCodeExist(course)) {
				bindingResult.rejectValue("code", "error.code", "Course with this code already exists.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/admin-course-form";
		}
		
		if (course.getLecturer()==null)
			course.setLecturer(null);
		else
			course.setLecturer(adminService.findByUserName(course.getLecturer().getUserName()));
		adminService.saveCourse(course);
		return "forward:/admin/course/list";
	}
	
	@GetMapping("/course/edit/{id}")
	public String updateCourse(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("lecturers", adminService.listAllLecturers());
		model.addAttribute("course", adminService.findCourseById(id));
		return "admin/admin-course-form";
	}
	
	@GetMapping("/course/delete/{id}")
	public String deleteCourse(@PathVariable("id") Integer id) {
		Course course = adminService.findCourseById(id);
		adminService.deleteCourse(course);
		return "forward:/admin/course/list";
	}

	@RequestMapping("/course/list")
	public String listCourses(@RequestParam("page") Optional<Integer> page, Model model) {
		int requestPage = page.orElse(1);
		Pageable pageable = PageRequest.of(requestPage-1, pageSize, Sort.by("code"));
		Page<Course> adminPage = adminService.findCoursePaginated(pageable);
		model.addAttribute("adminPage", adminPage);
		
        int totalPages = adminPage.getTotalPages();        
        if (totalPages > 0 ) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        
        model.addAttribute("endDate", adminService.listAllCoursesEndDate(pageable));
		model.addAttribute("courses", adminService.listAllCourses(pageable));
		return "admin/admin-view-course";
	}
	
}

package CA.CAPS.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService AdminService;
	
	@GetMapping("/lecturer/add")
	public String addLecturer(Model model) {
		Lecturer lecturer = new Lecturer();
		model.addAttribute("lecturer", lecturer);
		return "admin/admin-lecturer-form";
	}
	
	@GetMapping("/lecturer/save")
	public String saveLecturer(@ModelAttribute("lecturer") @Valid Lecturer lecturer, BindingResult bindingResult) {
		
		if (AdminService.isUserNameExist(lecturer)) {
			 bindingResult.rejectValue("userName", "error.userName", "Lecturer with this Username exists.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/admin-lecturer-form";
		}
		AdminService.saveLecturer(lecturer);
		return "forward:/admin/lecturer/list";
	}
	
	@GetMapping("/lecturer/edit/{id}")
	public String updateLecturer(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("lecturer", AdminService.findLecturerById(id));
		return "admin/admin-lecturer-form";
	}
	
	@GetMapping("/lecturer/delete/{id}")
	public String deleteLecturer(@PathVariable("id") Integer id) {
		Lecturer lecturer = AdminService.findLecturerById(id);
		AdminService.removeLecturerFromCourses(lecturer);
		AdminService.deleteLecturer(lecturer);
		return "forward:/admin/lecturer/list";
	}
	
	@GetMapping("/lecturer/list")
	public String listLecturers(Model model) {
		model.addAttribute("lecturers", AdminService.listAllLecturers());
		return "admin/admin-view-lecturer";
	}
	
	@GetMapping("/course/add")
	public String addCourse(Model model) {
		
		Course course = new Course();
		model.addAttribute("course", course);
		model.addAttribute("lecturers", AdminService.listAllLecturers());
		return "admin/admin-course-form";

	}
	
	@GetMapping("/course/save")
	public String saveCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, Model model) {
		
		model.addAttribute("lecturers", AdminService.listAllLecturers());
		
		if (AdminService.isCodeExist(course)) {
				bindingResult.rejectValue("code", "error.code", "Course with this code already exists.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/admin-course-form";
		}
		
		if (course.getLecturer()==null)
			course.setLecturer(null);
		else
			course.setLecturer(AdminService.findByUserName(course.getLecturer().getUserName()));
		AdminService.saveCourse(course);
		return "forward:/admin/course/list";
	}
	
	@GetMapping("/course/edit/{id}")
	public String updateCourse(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("lecturers", AdminService.listAllLecturers());
		model.addAttribute("course", AdminService.findCourseById(id));
		return "admin/admin-course-form";
	}
	
	@GetMapping("/course/delete/{id}")
	public String deleteCourse(@PathVariable("id") Integer id) {
		Course course = AdminService.findCourseById(id);
		AdminService.deleteCourse(course);
		return "forward:/admin/course/list";
	}

	@GetMapping("/course/list")
	public String listCourses(Model model) {
		model.addAttribute("courses", AdminService.listAllCoursesOrderByCode());
		return "admin/admin-view-course";
	}
	
}

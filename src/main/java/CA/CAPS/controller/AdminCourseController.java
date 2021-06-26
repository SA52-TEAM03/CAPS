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
import CA.CAPS.service.CourseService;
import CA.CAPS.service.LecturerService;

@Controller
@RequestMapping("/admin/course")
public class AdminCourseController {
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	LecturerService lecturerService;
	
	@GetMapping("/add")
	public String addCourse(Model model) {
		
		Course course = new Course();
		model.addAttribute("course", course);
		model.addAttribute("lecturers", lecturerService.listAllLecturers());
		return "admin/courseform";

	}
	
	@GetMapping("/save")
	public String saveCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult, Model model) {
		
		model.addAttribute("lecturers", lecturerService.listAllLecturers());
		
		if (courseService.isCodeExist(course)) {
				bindingResult.rejectValue("code", "error.code", "Course with this code already exists.");
		}
		
		if (bindingResult.hasErrors()) {
			return "admin/courseform";
		}
		
		if (course.getLecturer()==null)
			course.setLecturer(null);
		else
			course.setLecturer(lecturerService.findByUserName(course.getLecturer().getUserName()));
		courseService.saveCourse(course);
		return "forward:/admin/course/list";
	}
	
	@GetMapping("/edit/{id}")
	public String updateCourse(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("lecturers", lecturerService.listAllLecturers());
		model.addAttribute("course", courseService.findById(id));
		return "admin/courseform";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCourse(@PathVariable("id") Integer id) {
		Course course = courseService.findById(id);
		courseService.deleteCourse(course);
		return "forward:/admin/course/list";
	}

	@GetMapping("/list")
	public String listCourses(Model model) {
		model.addAttribute("courses", courseService.listAllCoursesOrderByCode());
		return "admin/course";
	}
}

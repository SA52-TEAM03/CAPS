package CA.CAPS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

	@GetMapping("/grades")
	public String showGrades(Model model) {
		
		return "student-grades-gpa";
	}
	
	@GetMapping("/courses")
	public String viewCourses(Model model) {
		
		return "student-view-courses";
	}
	
	@GetMapping("/enroll-course")
	public String enrollCourse(Model model) {
		
		return "student-enroll-course";
	}
}

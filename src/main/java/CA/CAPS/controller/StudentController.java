package CA.CAPS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;
import CA.CAPS.service.CourseServiceImpl;
import CA.CAPS.service.EnrolmentServiceImpl;
import CA.CAPS.service.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private EnrolmentServiceImpl enrolmentService;
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@Autowired
	private CourseServiceImpl courseService;

	@GetMapping("/grades")
	public String showGrades(Model model) {
		
		//To update the student after implement log in function
		List<Enrolment> enrolments = enrolmentService.findByStudent(studentService.getById(4));
				
		model.addAttribute("enrolments", enrolments);
		
		return "student-grades-gpa";
	}
	
	@GetMapping("/courses")
	public String viewCourses(Model model) {
		
		//To update the student after implement log in function
	    List<Course> coursesTakenByStudent = enrolmentService.findCourseByStudent(studentService.getById(4));
	    
	    List<Course> allCourses = courseService.findAll();
	    
	    List<Course> coursesNotTakenByStudent = new ArrayList<Course>();
	    
	    for(Course course : allCourses) {
	    	
	    	if(!coursesTakenByStudent.contains(course)) {
	    		coursesNotTakenByStudent.add(course);
	    	}	    	
	    }
	    
	    model.addAttribute("courses", coursesNotTakenByStudent);
		
		return "student-view-courses";
	}
	
	@RequestMapping(value = "/enroll-course/{id}")
	public String enrollCourse(@PathVariable("id") Integer id) {
		
		if(studentService.checkCourseAvailability(id)) {
			
			//To update after log in function is implemented, to get the id of student currently log in
			Student student = studentService.getById(4);
			
			studentService.enrollCourse(student.getId(), id);
			
			return "student-enroll-course-success";
		}
		
		return "student-enroll-course-error";
		
	}
}



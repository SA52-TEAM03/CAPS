package CA.CAPS.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;
import CA.CAPS.service.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
		
	
	@Autowired
	private StudentServiceImpl studentService;	


	@RequestMapping("/grades")
	public String showGrades(Model model, HttpSession session) {
		
		Student student=(Student) session.getAttribute("usession");

		List<Enrolment> enrolments = studentService.findEnrolmentsByStudent(student);
		
		List<String> grades = new ArrayList<String>();
		
		for(Enrolment enrolment : enrolments) {					
			
			if(enrolment.getGrade() == null) {				
				grades.add(" ");				
			}else {				
				grades.add(String.valueOf(enrolment.getGrade()));				
			}			
		}		
				
		model.addAttribute("enrolments", enrolments);
		model.addAttribute("grades", grades);
		
		return "student/student-grades-gpa";
	}
	
	@RequestMapping("/courses")
	public String viewCourses(Model model, HttpSession session) {
		
		Student student=(Student) session.getAttribute("usession");

		List<Course> coursesTakenByStudent = studentService.findCoursesEnrolledByStudent(student);
	    
	    List<Course> allCourses = studentService.listAllCourses();
	    
	    List<Course> coursesNotTakenByStudent = new ArrayList<Course>();
	    
	    for(Course course : allCourses) {
	    	
	    	if(!coursesTakenByStudent.contains(course)) {
	    		coursesNotTakenByStudent.add(course);
	    	}	    	
	    }
	    
	    model.addAttribute("courses", coursesNotTakenByStudent);
		
		return "student/student-view-courses";
	}
	
	@RequestMapping(value = "/enroll-course/{id}")
	public String enrollCourse(@PathVariable("id") Integer id, HttpSession session) {
		
		if(studentService.checkCourseAvailability(id)) {
			
			Student student=(Student) session.getAttribute("usession");
			
			studentService.enrollCourse(student.getId(), id);
			
			return "student/student-enroll-course-success";
		}
		
		return "student/student-enroll-course-error";
		
	}
}



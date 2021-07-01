package CA.CAPS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;
import CA.CAPS.service.MailService;
import CA.CAPS.service.StudentServiceImpl;
import CA.CAPS.util.GradeMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
		
	
	@Autowired
	private StudentServiceImpl studentService;	
	
	@Autowired
	MailService mailservice;


	@RequestMapping("/grades")
	public String showGrades(Model model) {
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();

		Student student=studentService.findByUserName(username);
		
		List<Enrolment> enrolments = studentService.findEnrolmentsByStudent(student);
		
		List<Double> gradePoints = new ArrayList<Double>();
		
		List<String> grades = new ArrayList<String>();
		
		Double gpa = studentService.getGPAOfStudent(student);
		
		for(Enrolment enrolment : enrolments) {					
			
			if(enrolment.getGrade() == null) {				
				gradePoints.add(null);	
				grades.add("");
			}else {				
				gradePoints.add(GradeMapping.getGrade(enrolment.getGrade()).getGradePoint());	
				grades.add(GradeMapping.getGrade(enrolment.getGrade()).getGrade());	
			}	
					
		}		
				
		model.addAttribute("enrolments", enrolments);
		model.addAttribute("gradePoints", gradePoints);
		model.addAttribute("grades", grades);
		model.addAttribute("gpa", gpa);
		
		return "student/student-grades-gpa";
	}
	
	@RequestMapping("/courses/list")
	public String viewCourses(@RequestParam("page") Optional<Integer> page, Model model) {
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();

		Student student=studentService.findByUserName(username);
		
		List<Course> coursesTakenByStudent = studentService.findCoursesEnrolledByStudent(student);
	    	    
	    Pageable pageable = PageRequest.of(page.orElse(1) - 1, 5);
	    
	    Page<Course> coursesNotTakenByStudent = studentService.findCoursesNotIn(coursesTakenByStudent, pageable);	    
	    
	    List<Course> coursesNotTakenByStudentPageCourse = coursesNotTakenByStudent.getContent();
	    
	    model.addAttribute("courses", coursesNotTakenByStudentPageCourse);
	    
	    model.addAttribute("coursePage", coursesNotTakenByStudent);
		
		return "student/student-view-courses";
	}
	
	@RequestMapping(value = "/enroll-course/{id}")
	public String enrollCourse(@PathVariable("id") Integer id) {
		
		if(studentService.checkCourseAvailability(id)) {
			
			String username=SecurityContextHolder.getContext().getAuthentication().getName();

			Student student=studentService.findByUserName(username);	
			
			Course course = studentService.getCourseById(id);
			
			if(student!=null && course!=null) {
				
				try {
					
					studentService.enrollCourse(student.getId(), id);
					
				}catch(Exception e) {
					
					return "forward:/student/grades";
				}
				
				studentService.enrollCourse(student.getId(), id);	
				
				String emailSubject = "Course Enrollment Successful";
				
				String emailContent = "You have successfully enrolled for " + course.getCode() + " - " + course.getName() + ".";
				
				mailservice.sendMail(student.getUserName(), emailSubject, emailContent);			
				
				return "student/student-enroll-course-success";
			}
			
		}
		
		return "student/student-enroll-course-error";
		
	}
}



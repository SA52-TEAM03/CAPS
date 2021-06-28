package CA.CAPS.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.service.CourseService;
import CA.CAPS.service.EnrolmentService;
import CA.CAPS.service.LecturerService;
import CA.CAPS.service.StudentService;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	@Autowired
	private LecturerService ls;
	
	@Autowired
	private CourseService cs;
	
	@Autowired
	private EnrolmentService es;
	
	@Autowired
	private StudentService ss;
	
	@GetMapping("/index")
	public String getHomePage(Model model, HttpSession session) {
		Lecturer lecturer = (Lecturer) session.getAttribute("usession");
		model.addAttribute("lecturer", ls.findLecturer(lecturer.getId()));
		return "lecturer/lecturer-index";
	}
	
	@GetMapping("/courses")
	public String getLecturerCourse(Model model, HttpSession session) {
		Lecturer lecturer = (Lecturer) session.getAttribute("usession");
		int id = lecturer.getId();
		model.addAttribute("courses", cs.findLecturerCourses(id));
		model.addAttribute("lecturer", ls.findLecturer(id));
		return "lecturer/lecturer-view-courses";
	}
	
	@GetMapping("/enrolment/{courseId}")
	public String getCourseEnrolment(@PathVariable("courseId") int courseId, Model model, HttpSession session) {
		Lecturer lecturer = (Lecturer) session.getAttribute("usession");
		int id = lecturer.getId();
		
		if(courseId == 0) {
			model.addAttribute("students", new ArrayList<>());
			model.addAttribute("course", new Course());
			model.addAttribute("grades", new ArrayList<>());
		} 
		else {
			model.addAttribute("students", es.findStudentsByCourse(courseId));
			model.addAttribute("course", cs.findById(courseId));
			model.addAttribute("grades", es.findGradesByCourse(courseId));
		}
		
		model.addAttribute("lecturer", ls.findLecturer(id));
		model.addAttribute("lecturerCourses", cs.findLecturerCourses(id));
		return "lecturer/lecturer-view-enrolment";
	}
	
	@GetMapping("/student/{studentId}")
	public String getStudent(@PathVariable("studentId") int studentId, Model model, HttpSession session) {
		Lecturer lecturer = (Lecturer) session.getAttribute("usession");
		int id = lecturer.getId();
		
		model.addAttribute("lecturer", ls.findLecturer(id));
		model.addAttribute("student", ss.getById(studentId));
		
		List<Enrolment> enrols = es.findEnrolByStudent(studentId);
		List<Course> courses = cs.listAllCourses();
		
		List<String> courseName = new ArrayList<>();
		for(Enrolment enrol : enrols) {
			for(Course course : courses) {
				if(enrol.getCourse().getId() == course.getId()) {
					courseName.add(course.getName());
				}
			}
		}
		model.addAttribute("enrolments", enrols);
		model.addAttribute("courseName", courseName);
		model.addAttribute("grades", es.findGradeByStudent(studentId));
		return "lecturer/lecture-view-student";
	}
	
	@GetMapping("/save/{studentId}/{courseId}")
	public String saveGrade(@RequestParam(value="grade") int grade,
			@PathVariable("studentId") int studentId, 
			@PathVariable("courseId") int courseId) {
		
		List<Enrolment> enrolments = es.findEnrolByStudent(studentId);
		for(Enrolment enrolment : enrolments) {
			if(enrolment.getCourse().getId() == courseId) {
				enrolment.setGrade(grade);
				es.save(enrolment);
			}
		}
		
		return ("forward:/lecturer/student/" + studentId);
	}
}


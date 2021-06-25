package CA.CAPS.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping(path = "/login")
	public String login(Model model) {
		Lecturer lecturer = new Lecturer();
		model.addAttribute("lecturer", lecturer);
		return "lecturer/lecturer-login";
	}
	
	@GetMapping("/authenticate")
	public String authenticate(@ModelAttribute("lecturer") Lecturer lec, Model model, HttpSession session) {
		
		if(ls.authenticateLecturer(lec.getUserName(), lec.getPassword())) {
			Lecturer lecturer = ls.findLecturerByUserName(lec.getUserName());
			session.setAttribute("lsession", lecturer);
			int id = lecturer.getId();
			
			return ("forward:/lecturer/" + id);
		}
		
		return "index";
	}
	
	@RequestMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "forward:/lecturer/login";
	}
	
	@GetMapping("/{id}") //need to setup HttpSession subsequently
	public String getHomePage(@PathVariable("id") int id, Model model) {
		model.addAttribute("lecturer", ls.findLecturer(id));
		return "lecturer/lecturer-index";
	}
	
	@GetMapping("/courses/{id}")
	public String getLecturerCourse(@PathVariable("id") int id, Model model) {
		model.addAttribute("courses", cs.findLecturerCourses(id));
		model.addAttribute("lecturer", ls.findLecturer(id));
		return "lecturer/lecturer-view-courses";
	}
	
	@GetMapping("/enrolment/{id}/{courseId}")
	public String getCourseEnrolment(@PathVariable("id") int id, @PathVariable("courseId") int courseId, Model model) {
		model.addAttribute("students", es.findStudentsByCourse(courseId));
		model.addAttribute("lecturer", ls.findLecturer(id));
		model.addAttribute("course", cs.getById(courseId));
		model.addAttribute("grades", es.findGradesByCourse(courseId));
		model.addAttribute("lecturerCourses", cs.findLecturerCourses(id));
		return "lecturer/lecturer-view-enrolment";
	}
	
	@GetMapping("/student/{id}/{studentId}")
	public String getStudent(@PathVariable("id") int id, @PathVariable("studentId") int studentId, Model model) {
		
		model.addAttribute("lecturer", ls.findLecturer(id));
		model.addAttribute("student", ss.getById(studentId));
		
		List<Enrolment> enrols = es.findEnrolByStudent(studentId);
		List<Course> courses = cs.findAll();
		
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
	
	@GetMapping("/save/{id}/{studentId}/{courseId}")
	public String saveGrade(@RequestParam(value="grade") int grade,
			@PathVariable("id") int lecturerId,
			@PathVariable("studentId") int studentId, 
			@PathVariable("courseId") int courseId) {
		
		List<Enrolment> enrolments = es.findEnrolByStudent(studentId);
		for(Enrolment enrolment : enrolments) {
			if(enrolment.getCourse().getId() == courseId) {
				enrolment.setGrade(grade);
				es.save(enrolment);
			}
		}
		
		return ("forward:/lecturer/student/" + lecturerId + "/" + studentId);
	}
}


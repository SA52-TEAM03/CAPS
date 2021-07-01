package CA.CAPS.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.service.LecturerService;
import CA.CAPS.util.GradeCount;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	@Autowired
	private LecturerService lecturerService;
	
	static int pageSize = 5;

	@GetMapping("/courses")
	public String getLecturerCourse(@RequestParam("page") Optional<Integer> page, Model model) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Lecturer lecturer = lecturerService.findLecturerByUserName(username);
		
		Pageable pageable = PageRequest.of(page.orElse(1) - 1, pageSize);
		Page<Course> lectPage = lecturerService.coursePageForLecturer(pageable, lecturer);
		List<Course> courses = lectPage.getContent();
		
		model.addAttribute("lectPage", lectPage);
		model.addAttribute("courses", courses);
	
		return "lecturer/lecturer-view-courses";
	}

	@GetMapping("/enrolment/{courseId}")
	public String getCourseEnrolment(@PathVariable("courseId") int courseId, 
			@RequestParam("page") Optional<Integer> page,
			Model model) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Lecturer lecturer = lecturerService.findLecturerByUserName(username);
		
		Course course;
		int id = lecturer.getId();
		
		if(courseId == 0) {
			List<Course> courses = lecturerService.findLecturerCourses(id);
			course = courses.get(0);
		}
		else {
			
			course = lecturerService.findById(courseId);
		}
		
		Pageable pageable = PageRequest.of(page.orElse(1) - 1, pageSize);
		Page<Student> lectPage = lecturerService.enrolmentPageForLecturer(pageable, course);
		List<Student> students = lectPage.getContent();
		Page<Integer> marks = lecturerService.marksList(pageable, course);
		List<Integer> grades = marks.getContent();

		model.addAttribute("lecturerCourses", lecturerService.findLecturerCourses(id));
		model.addAttribute("course", lecturerService.findById(course.getId()));
		model.addAttribute("grades", grades);
		model.addAttribute("lectPage", lectPage);
		model.addAttribute("students", students);
		
		return "lecturer/lecturer-view-enrolment";
	}

	@GetMapping("/student/{studentId}")
	public String getStudent(@PathVariable("studentId") int studentId, Model model) {

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Lecturer lecturer = lecturerService.findLecturerByUserName(username);

		int id = lecturer.getId();

		model.addAttribute("lecturer", lecturerService.findLecturer(id));
		model.addAttribute("student", lecturerService.getById(studentId));

		List<Enrolment> enrols = lecturerService.findEnrolByStudent(studentId);
		List<Course> courses = lecturerService.listAllCourses();

		List<String> courseName = new ArrayList<>();
		for (Enrolment enrol : enrols) {
			for (Course course : courses) {
				if (enrol.getCourse().getId() == course.getId()) {
					courseName.add(course.getName());
				}
			}
		}
		model.addAttribute("enrolments", enrols);
		model.addAttribute("courseName", courseName);
		model.addAttribute("grades", lecturerService.findGradeByStudent(studentId));
		return "lecturer/lecturer-view-student";
	}

	@GetMapping("/save/{studentId}/{courseId}")
	public String saveGrade(@RequestParam(value = "grade") int grade, @PathVariable("studentId") int studentId,
			@PathVariable("courseId") int courseId) {

		List<Enrolment> enrolments = lecturerService.findEnrolByStudent(studentId);
		for (Enrolment enrolment : enrolments) {
			if (enrolment.getCourse().getId() == courseId) {
				enrolment.setGrade(grade);
				lecturerService.save(enrolment);
			}
		}

		return ("forward:/lecturer/student/" + studentId);
	}

	@GetMapping("/plot/{courseId}")
	public String plotGrades(@PathVariable("courseId") int courseId, ModelMap model) {

		List<GradeCount> data = lecturerService.getDataPoints(courseId);
		Course course = lecturerService.findById(courseId);
		model.addAttribute("data", data);
		model.addAttribute("course", course);
		return "lecturer/plot";
	}
}

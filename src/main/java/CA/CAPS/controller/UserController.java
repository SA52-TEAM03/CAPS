package CA.CAPS.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.CourseRepository;
import CA.CAPS.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService uservice;

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	public void setUserService(UserService us) {
		this.uservice = us;
	}

	@RequestMapping(path = "/login")
	public String login(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "login";
	}

	@RequestMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "forward:/";
	}

	@RequestMapping(path = "/authenticate")
	public String authenticate(@ModelAttribute("student") Student student, Model model, HttpSession session) {
		Lecturer lecturer = new Lecturer();
		lecturer.setUserName(student.getUserName());
		lecturer.setPassword(student.getPassword());
		if (uservice.authenticate(student)) {
			Student u = uservice.findByStudentUserName(student.getUserName());
			session.setAttribute("usession", u);
			return "forward:/student/grades";
		} else if (uservice.authenticate(lecturer)) {
			Lecturer u = uservice.findByLecturerUserName(lecturer.getUserName());
			session.setAttribute("usession", u);
			int id = u.getId();
			return ("forward:/lecturer/" + id);}
		return "login";
	}
}

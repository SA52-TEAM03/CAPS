package CA.CAPS.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.service.MailService;
import CA.CAPS.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService uservice;

	@Autowired
	MailService mailservice;

	@Autowired
	public void setUserService(UserService us) {
		this.uservice = us;
	}

	@GetMapping(path = "/register")
	public String register(Model model) {
		return "register";
	}

	@PostMapping("/regist")
	public String register(String userName, String vcode, String firstName, String lastName, String password,
			Model model, HttpSession session) {

		Student student = uservice.findStudentByUserName(userName);

		if (student != null) {
			model.addAttribute("message", "The student already exists.");
			return "register";
		}
		
		String email = (String) session.getAttribute("email");
		String code = (String) session.getAttribute("code");

		if (userName.equals(email) && vcode.equals(code)) {
			Student newstudent = new Student(userName, password, firstName, lastName);
			uservice.createStudent(newstudent);
			Student s = uservice.findStudentByUserName(userName);
			session.setAttribute("usession", s);
			return "redirect:/student/grades";
		} else {
			model.addAttribute("message", "Incorrect email or validation code.");
			return "register";
		}
	}

	@PostMapping("/sendEmail/{email}")
	@ResponseBody
	public String sendEmail(@PathVariable("email") String email, HttpSession session) {

		if (email.length() > 10 && email.substring(email.length() - 10, email.length()).equals("@u.nus.edu")) {

			String code = mailservice.ValidationCode();
			session.setAttribute("email", email);
			session.setAttribute("code", code);

			String subject = "validation code for CAPS registration";
			String text = "Your registration verification code is: " + code;

			if (mailservice.sendMail(email, subject, text)) {
				return "The verification code has been sent to your email.";
			} else {
				return "Oops, something wrong with our mail server, please register after a while";
			}
		} else {
			return "You can only register with your nus email.";
		}
	}

	@RequestMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "forward:/";
	}

	@RequestMapping(path = "/authenticate")
	public String authenticate(String userName, String password, HttpSession session, Model model) {

		Student student = new Student(userName, password);
		Lecturer lecturer = new Lecturer(userName, password);
		Admin admin = new Admin(userName, password);

		if (uservice.authenticate(student)) {
			Student u = uservice.findStudentByUserName(student.getUserName());
			session.setAttribute("usession", u);
			return "redirect:/student/grades";

		} else if (uservice.authenticate(lecturer)) {
			Lecturer u = uservice.findLecturerByUserName(lecturer.getUserName());
			session.setAttribute("usession", u);
			int id = u.getId();
			return ("redirect:/lecturer/" + id);

		} else if (uservice.authenticate(admin)) {
			Admin u = uservice.findAdminByUserName(admin.getUserName());
			session.setAttribute("usession", u);
			return "redirect:/AdminStudent/list";
		}

		model.addAttribute("message", "Email or password error");
		return "forward:/";
	}
}

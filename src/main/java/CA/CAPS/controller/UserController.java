package CA.CAPS.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.service.MailService;
import CA.CAPS.service.UserServiceImple;

@Controller
public class UserController {

	@Autowired
	UserServiceImple userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MailService mailservice;

	@RequestMapping(path = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(path = "/loginpage")
	public String login() {
		return "loginpage";
	}

	@RequestMapping(path = "/registerpage")
	public String registerpage() {
		return "registerpage";
	}

	@ResponseBody
	@RequestMapping(path = "/sendcode/{email}")
	public String sendcode(@PathVariable(value = "email", required = false) String email, HttpSession session) {
		mailservice.sendValidationCode(email, session);
		return "validation code has been send to your email";
	}

	@RequestMapping(path = "/register")
	public String register(String username, String vcode, String firstname, String lastname, String password,
			HttpSession session, Model model) {

		Student student = userService.findStudentByUserName(username);

		if (student != null) {
			model.addAttribute("message", "The user already exists.");
			return "register";
		}

		String email = (String) session.getAttribute("email");
		String code = (String) session.getAttribute("code");

		if (username.equals(email) && vcode.equals(code)) {
			Student newstudent = new Student(username, password, firstname, lastname);
			userService.saveStudent(newstudent);
			model.addAttribute("message", "register success!");
			String subject = "CAPS Account";
			String text = "Your CAPS Account has been created.\n" + "Username: " + username + "\n" + "Password: "
					+ password;
			mailservice.sendMail(username, subject, text);
			return "loginpage";
		} else {
			model.addAttribute("message", "Incorrect email or validation code.");
			return "register";
		}
	}

	@ResponseBody
	@RequestMapping(path = "/validation/{username}/{password}")
	public String validation(@PathVariable(value = "username", required = false) String username,
			@PathVariable(value = "password", required = false) String password) {
		Admin admin = userService.findAdminByUserName(username);
		Student student = userService.findStudentByUserName(username);
		Lecturer lecturer = userService.findLecturerByUserName(username);

		if (admin != null) {
			if (passwordEncoder.matches(password, admin.getPassword())) {
				return "valid";
			}
			return "password incorrect";
		} else if (student != null) {
			if (passwordEncoder.matches(password, student.getPassword())) {
				return "valid";
			}
			return "password incorrect";
		} else if (lecturer != null) {
			if (passwordEncoder.matches(password, lecturer.getPassword())) {
				return "valid";
			}
			return "password incorrect";
		} else {
			return "user not exist";
		}
	}
}

package CA.CAPS.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
			String role, String date, Model model, HttpSession session) {
		String email = (String) session.getAttribute("email");
		String code = (String) session.getAttribute("code");

		Object user = uservice.findByUserName(userName);

		if (user != null) {
			model.addAttribute("message", "The user already exists.");
			return "register";
		}

		if (userName.equals(email) && vcode.equals(code)) {
			if (role.equals("student")) {

				DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate enrollmentDate;

				if (date.equals("")) {
					model.addAttribute("message", "missing enrolmentDate for student.");
					return "register";
				} else {
					enrollmentDate = LocalDate.parse(date, df1);
				}

				Student newstudent = new Student(userName, password, firstName, lastName, enrollmentDate);
				uservice.createStudent(newstudent);
				Student s = uservice.findByStudentUserName(userName);
				session.setAttribute("usession", s);
				return "redirect:/student/grades";

			} else if (role.equals("lecturer")) {

				Lecturer newlecturer = new Lecturer(firstName, lastName, userName, password);
				uservice.createLecturer(newlecturer);
				Lecturer l = uservice.findByLecturerUserName(userName);
				session.setAttribute("usession", l);
				int id = l.getId();
				return ("redirect:/lecturer/" + id);

			} else if (role.equals("admin")) {

				Admin newadmin = new Admin(firstName, lastName, userName, password);
				uservice.createAdmin(newadmin);
				Admin a = uservice.findByAdminUserName(userName);
				session.setAttribute("usession", a);
				return "redirect:/AdminStudent/list";

			} else {

				return "register";
			}
		} else

		{

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
			Student u = uservice.findByStudentUserName(student.getUserName());
			session.setAttribute("usession", u);
			return "redirect:/student/grades";

		} else if (uservice.authenticate(lecturer)) {
			Lecturer u = uservice.findByLecturerUserName(lecturer.getUserName());
			session.setAttribute("usession", u);
			int id = u.getId();
			return ("redirect:/lecturer/" + id);

		} else if (uservice.authenticate(admin)) {
			Admin u = uservice.findByAdminUserName(admin.getUserName());
			session.setAttribute("usession", u);
			return "redirect:/AdminStudent/list";
		}

		model.addAttribute("message", "Email or password error");
		return "forward:/";
	}
}

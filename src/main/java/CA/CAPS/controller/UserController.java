package CA.CAPS.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.AdminRepository;
import CA.CAPS.repo.LecturerRepository;
import CA.CAPS.repo.StudentRepository;
import CA.CAPS.service.MailService;
import CA.CAPS.service.UserServiceImple;
import CA.CAPS.util.FaceUtil;

@Controller
public class UserController {

	@Autowired
	AdminRepository adminRepo;

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	LecturerRepository lecturerRepo;

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

	@GetMapping("/face_login_page")
	public String faceloginPage() {
		return "face_login_page";
	}

	@GetMapping("/face_register_page")
	public String faceregisterpage() {
		return "face_register_page";
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

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/faceLogin", method = RequestMethod.POST)
	public Map<String, Object> faceLogin(@RequestParam("snapData") String str, HttpServletRequest request,
			HttpSession session) throws Exception {

		String img_data = str.substring(22, str.length());
		String imageType = "BASE64";
		String groupIdList = "student,lecturer,admin";

		HashMap<String, String> options = new HashMap<String, String>();
		options.put("max_face_num", "1");
		options.put("match_threshold", "80");
		options.put("quality_control", "HIGH");
		options.put("liveness_control", "NORMAL");

		JSONObject res = FaceUtil.getClient().search(img_data, imageType, groupIdList, options);

		System.out.println(res.toString(2));

		Map<String, Object> map = JSON.parseObject(res.toString());

		if (map.get("result") == null) {
			return map;
		}

		String role = (String) ((Map) ((List) ((Map) map.get("result")).get("user_list")).get(0)).get("group_id");

		int user_id = Integer
				.parseInt((String) ((Map) ((List) ((Map) map.get("result")).get("user_list")).get(0)).get("user_id"));

		if (role.equals("admin")) {
			Admin admin = adminRepo.findById(user_id);
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(admin.getUserName(),
					admin.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

			authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(authReq);
			session.setAttribute("role", "Admin");
			session.setAttribute("user", admin);
			map.put("role", "admin");
			return map;
		}

		if (role.equals("student")) {
			Student student = studentRepo.findById(user_id);
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(student.getUserName(),
					student.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("student"));

			authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(authReq);
			session.setAttribute("role", "Student");
			session.setAttribute("user", student);
			map.put("role", "student");
			return map;
		}

		if (role.equals("lecturer")) {
			Lecturer lecturer = lecturerRepo.findById(user_id);
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
					lecturer.getUserName(), lecturer.getPassword(),
					AuthorityUtils.commaSeparatedStringToAuthorityList("lecturer"));

			authReq.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(authReq);
			session.setAttribute("role", "Lecturer");
			session.setAttribute("user", lecturer);
			map.put("role", "lecturer");
			return map;
		}
		return map;

	}

	@ResponseBody
	@RequestMapping(value = "/faceRegister")
	public Map<String, Object> faceRegister(@RequestParam("snapData") String str, HttpSession session) {

		String image = str.substring(22, str.length());
		String imageType = "BASE64";
		String groupId = (String) session.getAttribute("role");
		String userId;
		switch (groupId) {
		case "Student":
			Student student = (Student) session.getAttribute("user");
			userId = student.getId() + "";
			groupId = "student";
			break;

		case "Lecturer":
			Lecturer lecturer = (Lecturer) session.getAttribute("user");
			userId = lecturer.getId() + "";
			groupId = "lecturer";
			break;

		case "Admin":
			Admin admin = (Admin) session.getAttribute("user");
			userId = admin.getId() + "";
			groupId = "admin";
			break;

		default:
			groupId = "anonymous";
			userId = "anonymous";
			break;
		}

		HashMap<String, String> options = new HashMap<String, String>();
		options.put("quality_control", "HIGH");
		options.put("liveness_control", "NORMAL");

		JSONObject res = FaceUtil.getClient().addUser(image, imageType, groupId, userId, options);
		System.out.println(res.toString(2));

		Map<String, Object> map = JSON.parseObject(res.toString());
		return map;
	}
}

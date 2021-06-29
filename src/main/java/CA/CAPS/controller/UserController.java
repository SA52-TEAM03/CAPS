package CA.CAPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import CA.CAPS.service.UserServiceImple;

@Controller
public class UserController {

	@Autowired
	UserServiceImple uservice;

	@RequestMapping(path = "/")
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(path = "/loginpage")
	public String home(Model model) {
		return "loginpage";
	}

//	@ResponseBody
//	@RequestMapping(path = "/couseinfo/{date}")
//	public String home(@PathVariable("date") String date,Model model) {
//		String d=date;
//		String m="6";
//		String y="2021";
//		LocalDate ld=LocalDate.parse(y+"-"+m+"-"+d);
//		model.addAttribute("courses",cservice.needIformEnrollmentCourses(ld));
//		return "<p th:text=${c.name}> Course will start from </p><p th:text=${c.startDate}>, can enroll now.</p>";
//	}
}

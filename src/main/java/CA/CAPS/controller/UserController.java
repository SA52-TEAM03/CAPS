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

}

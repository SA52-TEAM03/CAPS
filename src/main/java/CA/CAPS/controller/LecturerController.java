package CA.CAPS.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CA.CAPS.domain.Lecturer;
import CA.CAPS.service.LecturerService;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	@Autowired
	LecturerService lecturerService;
	
	@GetMapping("/add")
	public String addLecturer(Model model) {
		Lecturer lecturer = new Lecturer();
		model.addAttribute("lecturer", lecturer);
		return "lecturerform";
	}
	
	@GetMapping("/save")
	public String saveLecturer(@ModelAttribute("lecturer") @Valid Lecturer lecturer, BindingResult bindingResult) {
		
		if (lecturerService.isUserNameExist(lecturer)) {
			 bindingResult.rejectValue("userName", "error.userName", "Lecturer with this Username exists.");
		}
		
		if (bindingResult.hasErrors()) {
			return "lecturerform";
		}
		lecturerService.saveLecturer(lecturer);
		return "forward:/lecturer/list";
	}
	
	@GetMapping("/edit/{id}")
	public String updateLecturer(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("lecturer", lecturerService.findById(id));
		return "lecturerform";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteLecturer(@PathVariable("id") Integer id) {
		Lecturer lecturer = lecturerService.findById(id);
		lecturerService.removeLecturerFromCourses(lecturer);
		lecturerService.deleteLecturer(lecturer);
		return "forward:/lecturer/list";
	}
	
	@GetMapping("/list")
	public String listLecturers(Model model) {
		model.addAttribute("lecturers", lecturerService.listAllLecturers());
		return "lecturer";
	}
}

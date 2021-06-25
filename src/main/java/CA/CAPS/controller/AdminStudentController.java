package CA.CAPS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CA.CAPS.domain.Student;
import CA.CAPS.repo.StudentRepository;

@Controller
@RequestMapping("/AdminStudent")
public class AdminStudentController {

		@Autowired
		StudentRepository srepo;
		
		@GetMapping("/list")
		public String listStudents(Model model) {
			model.addAttribute("students", srepo.findAll());
			
			return "adminHome";
		}
		
		@GetMapping("/add")
		public String addStudent(Model model) {
			Student student = new Student();
			model.addAttribute("student", student);
			return "AdminStudentForm";
		}
		
		@GetMapping("/save")
		public String saveStudent(@ModelAttribute Student student) {
			srepo.save(student);
			return "forward:/AdminStudent/list";
		}
		
		@GetMapping("/edit/{id}")
		public String showEditForm(Model model, @PathVariable("id") Integer id) {
			model.addAttribute("student", srepo.findById(id).get());
			return "AdminStudentForm";
		}
		
		@GetMapping("/delete/{id}")
		public String deleteMethod(Model model, @PathVariable("id") Integer id) {
			Student student = srepo.findById(id).get();
			srepo.delete(student);
			return "forward:/AdminStudent/list";
		}
}

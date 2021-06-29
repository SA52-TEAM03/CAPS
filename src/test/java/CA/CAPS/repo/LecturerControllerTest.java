package CA.CAPS.repo;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import CA.CAPS.controller.LecturerController;
import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.service.LecturerServiceImpl;


@ExtendWith(SpringExtension.class)
@WebMvcTest(LecturerController.class)
public class LecturerControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext webapplication;
	
	@Autowired
	private LecturerServiceImpl lecturerservice;
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webapplication).build();
	}
	
	@Test
	public void testgetLecturerusingid() {
		Lecturer lec = lecturerservice.findLecturer(8);
		assertEquals(lec.getFirstName(),"Liu");
	}

	
	@Test
	public void testcoursebylecusingid() {
		List <Course> course = lecturerservice.findLecturerCourses(8);
		for (Course c : course) {
			System.out.println(c);
		}		
	}
	
	@Test
	public void testLecturerforstudents() {
		List<Integer> grafe = lecturerservice.findGradesByCourse(5);
		List<Student> s = lecturerservice.findStudentsByCourse(5);
		
		for (Integer i : grafe) {
			System.out.println(i);
		}
		for (Student ss: s) {
			System.out.println(ss);
		}
	}
	

}

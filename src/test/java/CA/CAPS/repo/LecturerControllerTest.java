package CA.CAPS.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import CA.CAPS.CapsApplication;
import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.service.LecturerServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class LecturerControllerTest {

	@Autowired
	private LecturerServiceImpl lecturerservice;
	
	@Autowired
	private LecturerRepository lrepo;
	
	@Test
	@Order(1)
	
	public void testFindLecturerByuserName() {
		Lecturer l1 = new Lecturer("Tin","Tri","TriTin","123456");
		lrepo.save(l1);
		Lecturer t1 = lrepo.findLecturerByUserName("TriTin");
		assertEquals(l1.getUserName(),t1.getUserName());
	}
	
	@Test
	@Order(2)
	public void testGetLecturerId() {
		Lecturer l3 = new Lecturer("Liu","Fan","lfan","2134");
		System.out.println(l3.getId());
	}

	@Test
	@Order(3)
	public void testcoursebylecusingid() {
		List<Course> course = lecturerservice.findLecturerCourses(5);
		for (Course c : course) {
			System.out.println(c);
		}
	}

	@Test
	@Order(4)
	public void testLecturerforstudents() {
		List<Integer> grafe = lecturerservice.findGradesByCourse(5);
		List<Student> s = lecturerservice.findStudentsByCourse(5);

		for (Integer i : grafe) {
			System.out.println(i);
		}
		for (Student ss : s) {
			System.out.println(ss);
		}
	}

}

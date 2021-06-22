package CA.CAPS.repo;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import CA.CAPS.CapsApplication;
import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class test {

	LocalDate date = LocalDate.now();

	@Autowired
	private StudentRepository srepo;

	@Autowired
	private LecturerRepository lrepo;

	@Autowired
	private CourseRepository crepo;

	@Autowired
	private EnrolmentRepository erepo;

	@Test
	@Order(1)
	public void testCreation() {
		Student student1 = new Student("william", "123456", "firstName", "lastName", date);
		Student student2 = new Student("larry", "123456", "firstName", "lastName", date);
		Student student3 = new Student("min", "123456", "firstName", "lastName", date);
		Student student4 = new Student("andy", "123456", "firstName", "lastName", date);
		Student student5 = new Student("kaman", "123456", "firstName", "lastName", date);
		Student student6 = new Student("jiakuang", "123456", "firstName", "lastName", date);
		Student student7 = new Student("xunlong", "123456", "firstName", "lastName", date);
		Student student8 = new Student("danlin", "123456", "firstName", "lastName", date);
		srepo.save(student1);
		srepo.save(student2);
		srepo.save(student3);
		srepo.save(student4);
		srepo.save(student5);
		srepo.save(student6);
		srepo.save(student7);
		srepo.save(student8);

		Course course1 = new Course("FOPCS", 50, 5);
		Course course2 = new Course("C#", 50, 5);
		Course course3 = new Course("JAVA", 50, 5);
		Course course4 = new Course(".NET", 50, 5);
		crepo.save(course1);
		crepo.save(course2);
		crepo.save(course3);
		crepo.save(course4);

		Lecturer lecturer1 = new Lecturer("liufan", "123456");
		Lecturer lecturer2 = new Lecturer("cherhwa", "123456");
		Lecturer lecturer3 = new Lecturer("tin", "123456");
		lrepo.save(lecturer1);
		lrepo.save(lecturer2);
		lrepo.save(lecturer3);

		course1.setLecturer(lecturer1);
		course2.setLecturer(lecturer3);
		course3.setLecturer(lecturer3);
		course4.setLecturer(lecturer2);

		course1.addEnrolments(student8);
		course2.addEnrolments(student7);
		course3.addEnrolments(student6);
		course4.addEnrolments(student5);
		crepo.save(course1);
		crepo.save(course2);
		crepo.save(course3);
		crepo.save(course4);

		student1.addEnrolments(course4);
		student2.addEnrolments(course3);
		student3.addEnrolments(course2);
		student4.addEnrolments(course1);
		srepo.save(student1);
		srepo.save(student2);
		srepo.save(student3);
		srepo.save(student4);
	}

	@Test
	@Order(2)
	public void testFind() {
		System.out.println(crepo.findCoursesByName("JAVA"));
		System.out.println(lrepo.findById(15));
		System.out.println(srepo.findById(1));
	}
	
	@Test
	@Order(3)
	public void testDelete() {
		crepo.deleteAll();
	}
}

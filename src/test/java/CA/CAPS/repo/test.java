package CA.CAPS.repo;

import java.time.LocalDate;

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
import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.service.AdminServiceImpl;
import CA.CAPS.service.LecturerServiceImpl;
import CA.CAPS.service.StudentServiceImpl;
import CA.CAPS.service.UserServiceImple;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class test {

	LocalDate date = LocalDate.now();

	@Autowired
	private UserServiceImple userService;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private LecturerServiceImpl lecturerService;
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@Autowired
	private EnrolmentRepository erepo;
	
	@Autowired
	private CourseRepository crepo;

	@Test
	@Order(1)
	public void testCreation() {
		LocalDate date = LocalDate.now();
		
		Admin admin1=new Admin("esther", "tan", "admin1@email.com", "123456");
		Admin admin2=new Admin("megan", "wang", "admin2@email.com", "123456");
		userService.createAdmin(admin1);
		userService.createAdmin(admin2);

		Student student1 = new Student("student1@email.com", "123456", "william", "lastName", date);
		Student student2 = new Student("student2@email.com", "123456", "larry", "lastName", date);
		Student student3 = new Student("student3@email.com", "123456", "min", "lastName", date);
		Student student4 = new Student("student4@email.com", "123456", "andy", "lastName", date);
		Student student5 = new Student("student5@email.com", "123456", "kaman", "lastName", date);
		Student student6 = new Student("student6@email.com", "123456", "jiakuang", "lastName", date);
		Student student7 = new Student("student7@email.com", "123456", "xunlong", "lastName", date);
		Student student8 = new Student("student8@email.com", "123456", "danlin", "lastName", date);
		userService.createStudent(student1);
		userService.createStudent(student2);
		userService.createStudent(student3);
		userService.createStudent(student4);
		userService.createStudent(student5);
		userService.createStudent(student6);
		userService.createStudent(student7);
		userService.createStudent(student8);

		Lecturer lecturer1 = new Lecturer("Liu", "Fan", "lecturer1@email.com", "123456");
		Lecturer lecturer2 = new Lecturer("Cher", "Wa", "lecturer2@email.com", "123456");
		Lecturer lecturer3 = new Lecturer("Tri", "Tin", "lecturer3@email.com", "123456");
		userService.createLecturer(lecturer1);
		userService.createLecturer(lecturer2);
		userService.createLecturer(lecturer3);	
		
		Course course1 = new Course("COMP101", "FOPCS", 50, 5, LocalDate.of(2021, 8, 01), 5);
		Course course2 = new Course("COMP102", "C#", 50, 5, LocalDate.of(2021, 8, 01), 5);
		Course course3 = new Course("COMP103", "JAVA", 50, 5, LocalDate.of(2021, 8, 01), 5);
		Course course4 = new Course("COMP104", ".NET", 50, 5, LocalDate.of(2021, 8, 01), 5);
		adminService.saveCourse(course1);
		adminService.saveCourse(course2);
		adminService.saveCourse(course3);
		adminService.saveCourse(course4);
		
		course1.setLecturer(lecturer1);
		course2.setLecturer(lecturer3);
		course3.setLecturer(lecturer3);
		course4.setLecturer(lecturer2);
		adminService.saveCourse(course1);
		adminService.saveCourse(course2);
		adminService.saveCourse(course3);
		adminService.saveCourse(course4);
	
		Enrolment enrolment1=new Enrolment(student5,course4);
		Enrolment enrolment2=new Enrolment(student6,course3);
		Enrolment enrolment3=new Enrolment(student7,course2);
		Enrolment enrolment4=new Enrolment(student8,course1);
		Enrolment enrolment5=new Enrolment(student1,course1);
		Enrolment enrolment6=new Enrolment(student2,course2);
		Enrolment enrolment7=new Enrolment(student3,course3);
		Enrolment enrolment8=new Enrolment(student4,course4);

		erepo.save(enrolment1);
		erepo.save(enrolment2);
		erepo.save(enrolment3);
		erepo.save(enrolment4);
		erepo.save(enrolment5);
		erepo.save(enrolment6);
		erepo.save(enrolment7);
		erepo.save(enrolment8);
	}

	@Test
	@Order(2)
	public void testFind() {
		System.out.println(lecturerService.findById(15));
		System.out.println(studentService.findById(1));
	}
	
	@Test
	@Order(3)
	public void testDelete() {
		crepo.deleteAll();
	}
}

package CA.CAPS.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import CA.CAPS.domain.Student;

//To connect to Spring Framework
@ExtendWith(SpringExtension.class)
//To connect to project
@SpringBootTest(classes = CapsApplication.class)
// To create ordering among test methods
@TestMethodOrder(OrderAnnotation.class)
// To connect to configuration via application.properties
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class StudentRepositoryUnitTests {

	@Autowired
	StudentRepository studentRepository;

	LocalDate date = LocalDate.now();

	@Test
	@Order(1)
	public void testCreateStudent() {
		Student student1 = new Student("william@u.nus.edu", "123456", "william", "lastName", date);

		Student expected = studentRepository.save(student1);

		assertNotNull(expected);
	}

	@Test
	@Order(2)
	public void testFindUserByUserName() {
		Student student2 = new Student("larry@u.nus.edu", "123456", "larry", "lastName", date);
		Student expected = studentRepository.save(student2);

		String userName = "larry@u.nus.edu";
		Student actual = studentRepository.findUserByUserName(userName);

		assertEquals(expected.getUserName(), actual.getUserName());
	}

	@Test
	@Order(3)
	public void testFindUserByDifferentUserName() {
		Student student3 = new Student("min@u.nus.edu", "123456", "min", "lastName", date);
		Student expected = studentRepository.save(student3);

		String userName = "larry@u.nus.edu";
		Student actual = studentRepository.findUserByUserName(userName);

		assertNotSame(expected.getUserName(), actual.getUserName());
	}

	@Test
	@Order(4)
	public void testFindUserByUserNameAndPassword() {
		Student student4 = new Student("andy@u.nus.edu", "123456", "andy", "lastName", date);
		Student expected = studentRepository.save(student4);

		String userName = "andy@u.nus.edu";
		String passWord = "123456";
		Student actual = studentRepository.findUserByUserNameAndPassword(userName, passWord);

		assertEquals(expected.getUserName(), actual.getUserName());
	}

	@Test
	@Order(5)
	public void testFindUserByWrongUserNameAndPassword() {
		Student student5 = new Student("kaman@u.nus.edu", "123456", "kaman", "lastName", date);
		Student expected = studentRepository.save(student5);

		String userName = "andy@u.nus.edu";
		String passWord = "123456";

		Student actual = studentRepository.findUserByUserNameAndPassword(userName, passWord);

		assertNotSame(expected.getUserName(), actual.getUserName());
	}

	@Test
	@Order(6)
	public void testDeleteUser() {

		Student student6 = new Student("jiakuang@u.nus.edu", "123456", "jiakuang", "lastName", date);

		studentRepository.delete(student6);

		String userName = "jiakuang@u.nus.edu";
		Student actual = studentRepository.findUserByUserName(userName);

		assertNull(actual);
	}
}

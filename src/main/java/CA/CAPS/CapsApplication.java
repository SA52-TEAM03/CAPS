package CA.CAPS;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.service.AdminServiceImpl;
import CA.CAPS.service.UserServiceImple;

@EnableAsync
@SpringBootApplication
public class CapsApplication {

	@Autowired
	private UserServiceImple userService;

	@Autowired
	private AdminServiceImpl adminService;

	public static void main(String[] args) {
		SpringApplication.run(CapsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			LocalDate date = LocalDate.now();

			Admin admin1 = new Admin("esther", "tan", "admin1@email.com", "123456");
			Admin admin2 = new Admin("megan", "wang", "admin2@email.com", "123456");
			userService.saveAdmin(admin1);
			userService.saveAdmin(admin2);

			Student student1 = new Student("student1@email.com", "123456", "William", "Wu", date);
			Student student2 = new Student("student2@email.com", "123456", "Larry", "Sim", date);
			Student student3 = new Student("student3@email.com", "123456", "Min Thein", "Kyaw", date);
			Student student4 = new Student("student4@email.com", "123456", "Andy", "Seah", date);
			Student student5 = new Student("student5@email.com", "123456", "Ka Man", "Chan", date);
			Student student6 = new Student("student6@email.com", "123456", "Jia Kuang", "Wong", date);
			Student student7 = new Student("student7@email.com", "123456", "Xunlong", "Zou", date);
			Student student8 = new Student("student8@email.com", "123456", "Dan Lin", "Chen", date);
			userService.saveStudent(student1);
			userService.saveStudent(student2);
			userService.saveStudent(student3);
			userService.saveStudent(student4);
			userService.saveStudent(student5);
			userService.saveStudent(student6);
			userService.saveStudent(student7);
			userService.saveStudent(student8);

			Lecturer lecturer1 = new Lecturer("Fan", "Liu", "lecturer1@email.com", "123456");
			Lecturer lecturer2 = new Lecturer("Cher Wah", "Tan", "lecturer2@email.com", "123456");
			Lecturer lecturer3 = new Lecturer("Tri Tin", "Nguyen", "lecturer3@email.com", "123456");
			userService.saveLecturer(lecturer1);
			userService.saveLecturer(lecturer2);
			userService.saveLecturer(lecturer3);

			Course course1 = new Course("COMP101", "FOPCS", 50, 5, LocalDate.of(2021, 8, 01), 5);
			Course course2 = new Course("COMP102", "OOPCS", 50, 5, LocalDate.of(2021, 8, 01), 5);
			Course course3 = new Course("COMP103", "SQL", 50, 5, LocalDate.of(2021, 8, 01), 5);
			Course course4 = new Course("COMP104", "ASP.NET", 50, 5, LocalDate.of(2021, 8, 01), 5);
			Course course5 = new Course("COMP201", "C++ 1", 50, 5, LocalDate.of(2021, 9, 01), 5);
			Course course6 = new Course("COMP202", "C++ 2", 50, 5, LocalDate.of(2021, 9, 01), 5);
			Course course7 = new Course("COMP203", "JAVA 1", 50, 5, LocalDate.of(2021, 9, 01), 5);
			Course course8 = new Course("COMP204", "JAVA 2", 50, 5, LocalDate.of(2021, 9, 01), 5);
			Course course9 = new Course("COMP205", "PYTHON 1", 50, 5, LocalDate.of(2021, 11, 01), 5);
			Course course10 = new Course("COMP206", "PYTHON 2", 50, 5, LocalDate.of(2021, 11, 01), 5);
			Course course11 = new Course("COMP301", "ANDROID 1", 50, 5, LocalDate.of(2021, 11, 01), 5);
			Course course12 = new Course("COMP302", "ANDROID 2", 50, 5, LocalDate.of(2021, 11, 01), 5);
			adminService.saveCourse(course1);
			adminService.saveCourse(course2);
			adminService.saveCourse(course3);
			adminService.saveCourse(course4);
			adminService.saveCourse(course5);
			adminService.saveCourse(course6);
			adminService.saveCourse(course7);
			adminService.saveCourse(course8);
			adminService.saveCourse(course9);
			adminService.saveCourse(course10);
			adminService.saveCourse(course11);
			adminService.saveCourse(course12);

			course1.setLecturer(lecturer1);
			course2.setLecturer(lecturer2);
			course3.setLecturer(lecturer3);
			course4.setLecturer(lecturer3);
			course5.setLecturer(lecturer1);
			course6.setLecturer(lecturer2);
			course7.setLecturer(lecturer3);
			course8.setLecturer(lecturer3);
			course9.setLecturer(lecturer1);
			course10.setLecturer(lecturer2);
			course11.setLecturer(lecturer3);
			course12.setLecturer(lecturer3);
			adminService.saveCourse(course1);
			adminService.saveCourse(course2);
			adminService.saveCourse(course3);
			adminService.saveCourse(course4);
			adminService.saveCourse(course5);
			adminService.saveCourse(course6);
			adminService.saveCourse(course7);
			adminService.saveCourse(course8);
			adminService.saveCourse(course9);
			adminService.saveCourse(course10);
			adminService.saveCourse(course11);
			adminService.saveCourse(course12);

			Enrolment enrolment1 = new Enrolment(student5, course4);
			Enrolment enrolment2 = new Enrolment(student6, course3);
			Enrolment enrolment3 = new Enrolment(student7, course2);
			Enrolment enrolment4 = new Enrolment(student8, course1);
			Enrolment enrolment5 = new Enrolment(student1, course1);
			Enrolment enrolment6 = new Enrolment(student2, course2);
			Enrolment enrolment7 = new Enrolment(student3, course3);
			Enrolment enrolment8 = new Enrolment(student4, course4);
			Enrolment enrolment11 = new Enrolment(student5, course5);
			Enrolment enrolment22 = new Enrolment(student6, course6);
			Enrolment enrolment33 = new Enrolment(student7, course7);
			Enrolment enrolment44 = new Enrolment(student8, course8);
			Enrolment enrolment55 = new Enrolment(student1, course8);
			Enrolment enrolment66 = new Enrolment(student2, course7);
			Enrolment enrolment77 = new Enrolment(student3, course6);
			Enrolment enrolment88 = new Enrolment(student4, course5);
			Enrolment enrolment111 = new Enrolment(student5, course9);
			Enrolment enrolment222 = new Enrolment(student6, course10);
			Enrolment enrolment333 = new Enrolment(student7, course12);
			Enrolment enrolment444 = new Enrolment(student8, course11);
			Enrolment enrolment555 = new Enrolment(student1, course11);
			Enrolment enrolment666 = new Enrolment(student2, course12);
			Enrolment enrolment777 = new Enrolment(student3, course10);
			Enrolment enrolment888 = new Enrolment(student4, course3);
			Enrolment enrolment1111 = new Enrolment(student1, course3);
			Enrolment enrolment2222 = new Enrolment(student2, course3);
			Enrolment enrolment3333 = new Enrolment(student4, course3);
			Enrolment enrolment4444 = new Enrolment(student5, course3);
			Enrolment enrolment5555 = new Enrolment(student7, course3);
			Enrolment enrolment6666 = new Enrolment(student8, course3);

			enrolment2.setGrade(30);
			enrolment7.setGrade(55);
			enrolment1111.setGrade(55);
			enrolment2222.setGrade(62);
			enrolment3333.setGrade(73);
			enrolment4444.setGrade(69);
			enrolment5555.setGrade(34);
			enrolment6666.setGrade(91);
			
			adminService.saveEnrolment(enrolment1);
			adminService.saveEnrolment(enrolment2);
			adminService.saveEnrolment(enrolment3);
			adminService.saveEnrolment(enrolment4);
			adminService.saveEnrolment(enrolment5);
			adminService.saveEnrolment(enrolment6);
			adminService.saveEnrolment(enrolment7);
			adminService.saveEnrolment(enrolment8);
			adminService.saveEnrolment(enrolment11);
			adminService.saveEnrolment(enrolment22);
			adminService.saveEnrolment(enrolment33);
			adminService.saveEnrolment(enrolment44);
			adminService.saveEnrolment(enrolment55);
			adminService.saveEnrolment(enrolment66);
			adminService.saveEnrolment(enrolment77);
			adminService.saveEnrolment(enrolment88);
			adminService.saveEnrolment(enrolment111);
			adminService.saveEnrolment(enrolment222);
			adminService.saveEnrolment(enrolment333);
			adminService.saveEnrolment(enrolment444);
			adminService.saveEnrolment(enrolment555);
			adminService.saveEnrolment(enrolment666);
			adminService.saveEnrolment(enrolment777);
			adminService.saveEnrolment(enrolment888);
			adminService.saveEnrolment(enrolment1111);
			adminService.saveEnrolment(enrolment2222);
			adminService.saveEnrolment(enrolment3333);
			adminService.saveEnrolment(enrolment4444);
			adminService.saveEnrolment(enrolment5555);
			adminService.saveEnrolment(enrolment6666);

		};
	}

}

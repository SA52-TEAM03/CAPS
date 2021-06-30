package CA.CAPS;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.EnrolmentRepository;
import CA.CAPS.service.AdminServiceImpl;
import CA.CAPS.service.UserServiceImple;

@SpringBootApplication
public class CapsApplication {

	@Autowired
	private UserServiceImple userService;
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private EnrolmentRepository erepo;

	public static void main(String[] args) {
		SpringApplication.run(CapsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
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
			Course course5 = new Course("COMP105", "FOPCS", 50, 5, LocalDate.of(2021, 9, 01), 5);
			Course course6 = new Course("COMP106", "C#", 50, 5, LocalDate.of(2021, 9, 01), 5);
			Course course7 = new Course("COMP107", "JAVA", 50, 5, LocalDate.of(2021, 9, 01), 5);
			Course course8 = new Course("COMP108", ".NET", 50, 5, LocalDate.of(2021, 9, 01), 5);			
			Course course12 = new Course("COMP112", "FOPCS", 50, 5, LocalDate.of(2021, 11, 01), 5);
			Course course9 = new Course("COMP109", "C#", 50, 5, LocalDate.of(2021, 11, 01), 5);
			Course course10 = new Course("COMP110", "JAVA", 50, 5, LocalDate.of(2021, 11, 01), 5);
			Course course11= new Course("COMP111", ".NET", 50, 5, LocalDate.of(2021, 11, 01), 5);
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
		
			Enrolment enrolment1=new Enrolment(student5,course4);
			Enrolment enrolment2=new Enrolment(student6,course3);
			Enrolment enrolment3=new Enrolment(student7,course2);
			Enrolment enrolment4=new Enrolment(student8,course1);
			Enrolment enrolment5=new Enrolment(student1,course1);
			Enrolment enrolment6=new Enrolment(student2,course2);
			Enrolment enrolment7=new Enrolment(student3,course3);
			Enrolment enrolment8=new Enrolment(student4,course4);
			Enrolment enrolment11=new Enrolment(student5,course5);
			Enrolment enrolment22=new Enrolment(student6,course6);
			Enrolment enrolment33=new Enrolment(student7,course7);
			Enrolment enrolment44=new Enrolment(student8,course8);
			Enrolment enrolment55=new Enrolment(student1,course8);
			Enrolment enrolment66=new Enrolment(student2,course7);
			Enrolment enrolment77=new Enrolment(student3,course6);
			Enrolment enrolment88=new Enrolment(student4,course5);
			Enrolment enrolment111=new Enrolment(student5,course9);
			Enrolment enrolment222=new Enrolment(student6,course10);
			Enrolment enrolment333=new Enrolment(student7,course12);
			Enrolment enrolment444=new Enrolment(student8,course11);
			Enrolment enrolment555=new Enrolment(student1,course11);
			Enrolment enrolment666=new Enrolment(student2,course12);
			Enrolment enrolment777=new Enrolment(student3,course10);
			Enrolment enrolment888=new Enrolment(student4,course9);

			erepo.save(enrolment1);
			erepo.save(enrolment2);
			erepo.save(enrolment3);
			erepo.save(enrolment4);
			erepo.save(enrolment5);
			erepo.save(enrolment6);
			erepo.save(enrolment7);
			erepo.save(enrolment8);
			erepo.save(enrolment11);
			erepo.save(enrolment22);
			erepo.save(enrolment33);
			erepo.save(enrolment44);
			erepo.save(enrolment55);
			erepo.save(enrolment66);
			erepo.save(enrolment77);
			erepo.save(enrolment88);
			erepo.save(enrolment111);
			erepo.save(enrolment222);
			erepo.save(enrolment333);
			erepo.save(enrolment444);
			erepo.save(enrolment555);
			erepo.save(enrolment666);
			erepo.save(enrolment777);
			erepo.save(enrolment888);
			
//			Course c1 = new Course("CourseA", 50, 5);
//			Course c2 = new Course("CourseB", 50, 4);
//			Course c3 = new Course("CourseC", 50, 3);
//			
//			courseService.save(c1);
//			courseService.save(c2);
//			courseService.save(c3);

//			Course c4 = new Course("CourseD", 50, 3);
//			Course c5 = new Course("CourseE", 50, 3);
//			Course c6 = new Course("CourseF", 50, 3);
//			
//			courseService.save(c4);
//			courseService.save(c5);
//			courseService.save(c6);		

//			Student s1 = new Student("st1", "st1", "Harry", "Potter", LocalDate.of(2021, 1, 1) );
//			Student s2 = new Student("st2", "st2", "Ron", "Weasley", LocalDate.of(2021, 1, 1) );
//			
//			studentService.save(s1);
//			studentService.save(s2);

//			Enrolment en1 = new Enrolment(studentService.getById(4), courseService.getById(1));
//			Enrolment en2 = new Enrolment(studentService.getById(4), courseService.getById(2));
//			Enrolment en3 = new Enrolment(studentService.getById(5), courseService.getById(2));
//			Enrolment en4 = new Enrolment(studentService.getById(5), courseService.getById(3));
//			
//			enrolmentService.save(en1);
//			enrolmentService.save(en2);
//			enrolmentService.save(en3);
//			enrolmentService.save(en4);

			// WW Test files
//			Student s1 = new Student("queeniewong@email.com", "password123", "Wong", "Queenie", LocalDate.now());
//			Student s2 = new Student("queenieleeg@email.com", "password123", "Lee", "Queenie", LocalDate.now());
//			Student s3 = new Student("queeniegoh@email.com", "password123", "Goh", "Queenie", LocalDate.now());
//			Student s4 = new Student("queeniezhou@email.com", "password123", "Zhou", "Queenie", LocalDate.now());
//			Student s5 = new Student("queeniecheng@email.com", "password123", "Cheng", "Queenie", LocalDate.now());
//
//			ArrayList<Student> studentlist = new ArrayList<Student>();
//			studentlist.add(s1);
//			studentlist.add(s2);
//			studentlist.add(s3);
//			studentlist.add(s4);
//			studentlist.add(s5);

//			srepo.saveAll(studentlist);

		};
	}

}

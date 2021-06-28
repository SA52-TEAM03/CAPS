package CA.CAPS;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.AdminRepository;
import CA.CAPS.repo.CourseRepository;
import CA.CAPS.repo.LecturerRepository;
import CA.CAPS.repo.StudentRepository;

@SpringBootApplication
public class CapsApplication {

	@Autowired
	private StudentRepository srepo;

	@Autowired
	private LecturerRepository lrepo;

	@Autowired
	private CourseRepository crepo;

	@Autowired
	private AdminRepository arepo;

	public static void main(String[] args) {
		SpringApplication.run(CapsApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			LocalDate date = LocalDate.now();

			Student student1 = new Student("william@u.nus.edu", "123456", "william", "lastName", date);
			Student student2 = new Student("larry@u.nus.edu", "123456", "larry", "lastName", date);
			Student student3 = new Student("min@u.nus.edu", "123456", "min", "lastName", date);
			Student student4 = new Student("andy@u.nus.edu", "123456", "andy", "lastName", date);
			Student student5 = new Student("kaman@u.nus.edu", "123456", "kaman", "lastName", date);
			Student student6 = new Student("jiakuang@u.nus.edu", "123456", "jiakuang", "lastName", date);
			Student student7 = new Student("xunlong@u.nus.edu", "123456", "xunlong", "lastName", date);
			Student student8 = new Student("danlin@u.nus.edu", "123456", "danlin", "lastName", date);
			srepo.save(student1);
			srepo.save(student2);
			srepo.save(student3);
			srepo.save(student4);
			srepo.save(student5);
			srepo.save(student6);
			srepo.save(student7);
			srepo.save(student8);

			Course course1 = new Course("COMP101", "FOPCS", 50, 5, LocalDate.of(2021, 8, 01), 5);
			Course course2 = new Course("COMP102", "C#", 50, 5, LocalDate.of(2021, 8, 01), 5);
			Course course3 = new Course("COMP103", "JAVA", 50, 5, LocalDate.of(2021, 8, 01), 5);
			Course course4 = new Course("COMP104", ".NET", 50, 5, LocalDate.of(2021, 8, 01), 5);

			crepo.save(course1);
			crepo.save(course2);
			crepo.save(course3);
			crepo.save(course4);

			Lecturer lecturer1 = new Lecturer("Liu", "Fan", "liufan@u.nus.edu", "123456");
			Lecturer lecturer2 = new Lecturer("Cher", "Wa", "cherhwa@u.nus.edu", "123456");
			Lecturer lecturer3 = new Lecturer("Tri", "Tin", "tin@u.nus.edu", "123456");
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
			
			Admin admin1=new Admin("esther", "tan", "esther@u.nus.edu", "123456");
			Admin admin2=new Admin("megan", "wang", "megan@u.nus.edu", "123456");
			arepo.save(admin1);
			arepo.save(admin2);
			
			
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
			Student s1 = new Student("queeniewong@email.com", "password123", "Wong", "Queenie", LocalDate.now());
			Student s2 = new Student("queenieleeg@email.com", "password123", "Lee", "Queenie", LocalDate.now());
			Student s3 = new Student("queeniegoh@email.com", "password123", "Goh", "Queenie", LocalDate.now());
			Student s4 = new Student("queeniezhou@email.com", "password123", "Zhou", "Queenie", LocalDate.now());
			Student s5 = new Student("queeniecheng@email.com", "password123", "Cheng", "Queenie", LocalDate.now());

			ArrayList<Student> studentlist = new ArrayList<Student>();
			studentlist.add(s1);
			studentlist.add(s2);
			studentlist.add(s3);
			studentlist.add(s4);
			studentlist.add(s5);

			srepo.saveAll(studentlist);

		};
	}

}

package CA.CAPS;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import CA.CAPS.domain.Student;
import CA.CAPS.repo.StudentRepository;
import CA.CAPS.service.CourseServiceImpl;
import CA.CAPS.service.StudentServiceImpl;

@SpringBootApplication
public class CapsApplication {
	
	@Autowired
	private CourseServiceImpl courseService;
//	
	@Autowired
	private StudentServiceImpl studentService;
	
	@Autowired
	private StudentRepository srepo;
//	
//	@Autowired
//	private EnrolmentServiceImpl enrolmentService;

	public static void main(String[] args) {
		SpringApplication.run(CapsApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args ->{
			
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
			
			//WW Test files
			Student s1 = new Student("queeniewong@email.com", "password123", "Wong", "Queenie", LocalDate.now());
			Student s2 = new Student("queenieleeg@email.com", "password123", "Lee", "Queenie", LocalDate.now());
			Student s3 = new Student("queeniegoh@email.com", "password123", "Goh", "Queenie", LocalDate.now());
			Student s4 = new Student("queeniezhou@email.com", "password123", "Zhou", "Queenie", LocalDate.now());
			Student s5 = new Student("queeniecheng@email.com", "password123", "Cheng", "Queenie", LocalDate.now());
			
			ArrayList<Student> studentlist = new ArrayList<Student>();
			studentlist.add(s1); studentlist.add(s2); studentlist.add(s3); studentlist.add(s4); studentlist.add(s5);
			
			srepo.saveAll(studentlist);
			
			
		};
	}

}

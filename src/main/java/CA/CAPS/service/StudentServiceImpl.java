package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.CourseRepository;
import CA.CAPS.repo.EnrolmentRepository;
import CA.CAPS.repo.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	EnrolmentRepository enrolmentRepo;
	

	@Override
	public void save(Student student) {
		
		studentRepo.save(student);	
		
	}

	@Override
	public Student findByUserName(String username) {
		return studentRepo.findUserByUserName(username);
	}

	@Override
	public Student findById(Integer id) {
		return studentRepo.getById(id);
	}
	
	@Override
	public boolean checkCourseAvailability(Integer id) {
		
		boolean result = false;
		
		Course course = courseRepo.getById(id);
					
		if(course.getEnrolments().size() < course.getSize()) {
			result = true;
		}
				
		return result;
		
	}
	
	@Override
	public void enrollCourse(int studentId, int courseId) {
		
		Enrolment enrolment = new Enrolment(studentRepo.getById(studentId), courseRepo.getById(courseId));
		
		enrolmentRepo.save(enrolment);
	}
	
	@Override
	public List<Enrolment> findEnrolmentsByStudent(Student student) {
		
		return enrolmentRepo.findByStudent(student);
	}
	
	@Override
	public List<Course> findCoursesEnrolledByStudent(Student student) {
		
		return enrolmentRepo.findCourseByStudent(student);
	}
	
	@Override
	public List<Course> listAllCourses() {
		return courseRepo.findAll();
	}
	

}

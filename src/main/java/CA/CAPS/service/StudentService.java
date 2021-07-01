package CA.CAPS.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;

public interface StudentService {
		
	public void save(Student student);
	
	public Student findById(Integer id);
	
	public boolean checkCourseAvailability(Integer id);
	
	public void enrollCourse(int studentId, int courseId);
	
	public List<Enrolment> findEnrolmentsByStudent(Student student);
	
	public List<Course> findCoursesEnrolledByStudent(Student student);
	
	public List<Course> listAllCourses();
	
	public Double getGPAOfStudent(Student student);

	public Student findByUserName(String username);
	
	public Course getCourseById(int courseId);
	
	public Page<Course> findCoursesNotIn(List<Course> courses, Pageable pageable);

}

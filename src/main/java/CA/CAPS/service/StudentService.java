package CA.CAPS.service;

import java.util.List;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;

public interface StudentService {
		
	public void save(Student student);
	
	public Student getById(Integer id);
	
	public boolean checkCourseAvailability(Integer id);
	
	public void enrollCourse(int studentId, int courseId);
	
	public List<Enrolment> findEnrolmentsByStudent(Student student);
	
	public List<Course> findCoursesEnrolledByStudent(Student student);
	
	public List<Course> listAllCourses();

}

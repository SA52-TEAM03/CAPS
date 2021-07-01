package CA.CAPS.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.util.GradeCount;

public interface LecturerService {

	public Lecturer findLecturer(int id);

	public Lecturer findLecturerByUserName(String userName);
	
	public List<Course> findLecturerCourses(int id);
	
	public List<Student> findStudentsByCourse(int id);
	
	public Course findById(Integer id);
	
	public List<Integer> findGradesByCourse(int id);
	
	public Student getById(Integer id);
	
	public List<Enrolment> findEnrolByStudent(int id);
	
	public List<Course> listAllCourses();
	
	public List<Integer> findGradeByStudent(int id);
	
	public void save(Enrolment enrolment);

	public List<GradeCount> getDataPoints(int id);
	
	public Page<Course> coursePageForLecturer(Pageable pageable, Lecturer lecturer);
	
	public Page<Student> enrolmentPageForLecturer(Pageable pageable, Course course);
}

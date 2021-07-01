package CA.CAPS.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;

public interface AdminService {

	public void deleteLecturer(Lecturer lecturer);
	public List<Lecturer> listAllLecturers();
	public Lecturer findLecturerByUserName(String name);
	public Lecturer findLecturerById(Integer id);
	public Boolean isLecturerExist(Lecturer lecturer);
	public void removeLecturerFromCourses(Lecturer Lecturer);
	public Page<Lecturer> lecturerPage(Pageable pageable);
	
	public void saveCourse(Course course);
	public void deleteCourse(Course course);
	public List<Course> listAllCourses();
	public Course findCourseById(Integer id);
	public Boolean isCourseCodeExist(Course course);
	public Page<Course> coursePage(Pageable pageable);
	
	public void deleteStudent(Student student);
	public List<Student> listStudents();
	public Student findStudentById(Integer id);
	public Boolean isStudentExist(Student student);
	public Page<Student> studentPage(Pageable pageable);
	
	public void saveEnrolment(Enrolment enrolment);
	public void deleteEnrolment(Enrolment enrolment);
	public List<Enrolment> listAllEnrolments();
	public List<Enrolment> findEnrolmentByStudentId(Integer id);	
	public List<Student> findNotEnrolStudentsByCourseId(Integer id);
	public List<Student> findEnrolStudentsByCourseId(Integer id);
	public void enrollStudentsInCourse(List<Student> students, int courseId);

}

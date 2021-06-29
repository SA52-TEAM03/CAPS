package CA.CAPS.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;

public interface AdminService {

	public void saveLecturer(Lecturer lecturer);
	public void deleteLecturer(Lecturer lecturer);
	public List<Lecturer> listAllLecturers();
	public List<Lecturer> listAllLecturers(Pageable pageable);
	public Page<Lecturer> findLecturerPaginated(Pageable pageable);
	public Lecturer findByUserName(String name);
	public Lecturer findLecturerById(Integer id);
	public Boolean isUserNameExist(Lecturer lecturer);
	public void removeLecturerFromCourses(Lecturer Lecturer);
	
	public void saveCourse(Course course);
	public void updateCourse(Course course);
	public void deleteCourse(Course course);
	public List<Course> listAllCourses();
	public List<Course> listAllCourses(Pageable pageable);
	public List<LocalDate> listAllCoursesEndDate();
	public List<LocalDate> listAllCoursesEndDate(Pageable pageable);
	public Page<Course> findCoursePaginated(Pageable pageable);
	public Course findCourseById(Integer id);
	public Course findByCode(String code);
	public Boolean isCourseCodeExist(Course course);
	
	public void saveStudent(Student student);
	public void updateStudent(Student student);
	public void deleteStudent(Student student);
	public List<Student> listStudents();
	public List<Student> listStudents(Pageable pageable);
	public Page<Student> findStudentPaginated(Pageable pageable);
	public Student findStudentById(Integer id);
	public Boolean isStudentExist(Student student);
	
	public void saveEnrolment(Enrolment enrolment);
	public void updateEnrolment(Enrolment enrolment);
	public void deleteEnrolment(Enrolment enrolment);
	public List<Enrolment> listAllEnrolments();
	
	public List<Enrolment> listAllEnrolments(Pageable pageable);
	public Page<Enrolment> findEnrolmentPaginated(Pageable pageable);
	public List<Enrolment> findEnrolmentByStudentId(Integer id);
	public Boolean isEnrolmentExist(Enrolment enrolment);
	
	public List<Student> findNotEnrolStudentsByCourseId(Integer id);
	public List<Student> findEnrolStudentsByCourseId(Integer id);
	public void enrollStudentsInCourse(List<Student> students, int courseId);
	
	public void updateCourseSize(int courseId);
	public void ReturnCourseSize(int courseId);
}

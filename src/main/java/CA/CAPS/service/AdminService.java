package CA.CAPS.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;

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
	
}

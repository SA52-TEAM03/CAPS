package CA.CAPS.service;

import java.util.List;

import CA.CAPS.domain.Course;

public interface CourseService {
	
	public void saveCourse(Course course);
	public void updateCourse(Course course);
	public void deleteCourse(Course course);
	public List<Course> listAllCourses();
	public List<Course> listAllCoursesOrderByCode();
	public Course findById(Integer id);
	public Course findByCode(String code);
	public Boolean isCodeExist(Course course);
	
	public void save(Course course);
	
	public Course getById(Integer id);
	
	public List<Course> findLecturerCourses(int id);

}

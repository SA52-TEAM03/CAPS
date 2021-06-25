package CA.CAPS.service;

import java.util.List;

import CA.CAPS.domain.Course;

public interface CourseService {
	
	public List<Course> findAll();
	
	public void save(Course course);
	
	public Course getById(Integer id);
	
	public List<Course> findLecturerCourses(int id);

}

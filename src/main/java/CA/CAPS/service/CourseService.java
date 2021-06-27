package CA.CAPS.service;

import java.util.List;

import CA.CAPS.domain.Course;

public interface CourseService {
	
	public List<Course> listAllCourses();
	public Course findById(Integer id);
	public List<Course> findLecturerCourses(int id);

}

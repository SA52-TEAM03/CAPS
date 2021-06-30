package CA.CAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CA.CAPS.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	@Query("SELECT c FROM Course c WHERE c.lecturer.id = :id")
	public List<Course> findCoursesByLecturerId(@Param("id") int id);
		
	public Course findCourseById(int id);
	public List<Course> findCoursesByName(String name);
	
}
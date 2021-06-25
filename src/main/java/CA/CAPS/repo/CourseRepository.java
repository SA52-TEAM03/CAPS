package CA.CAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CA.CAPS.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	List<Course> findCoursesByName(String name);
	@Query("SELECT c FROM Course c Order By c.code")
	List<Course> listAllCourseOrderByCode();
	Course findCourseByCode(String code);
	
}
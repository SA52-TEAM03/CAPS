package CA.CAPS.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CA.CAPS.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	@Query("Select c from Course c where c.name = :name")
	ArrayList<Course> findCoursesByName(@Param("name") String name);
}
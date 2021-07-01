package CA.CAPS.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findUserByUserNameAndPassword(String userName, String password);

	public Student findUserByUserName(String userName);
	
	@Query("SELECT s FROM Student s WHERE s NOT IN (:students)")
	public List<Student> findStudentsNotIn(@Param("students") List<Student> students);
	
	@Query("SELECT s FROM Student s JOIN s.enrolments e WHERE e.course = :course")
	public Page<Student> findByCourse(Pageable pageable, @Param("course") Course course);
}

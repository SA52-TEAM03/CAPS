package CA.CAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CA.CAPS.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findUserByUserNameAndPassword(String userName, String password);

	public Student findUserByUserName(String userName);
	
	@Query("SELECT s FROM Student s WHERE s NOT IN (:students)")
	public List<Student> findStudentsNotIn(@Param("students") List<Student> students);
	
	@Query("SELECT s FROM Student s WHERE s IN (:students)")
	public List<Student> findStudentsIn(@Param("students") List<Student> students);
}

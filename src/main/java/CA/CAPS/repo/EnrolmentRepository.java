package CA.CAPS.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;
import CA.CAPS.domain.enrolmentUPK;

public interface EnrolmentRepository extends JpaRepository<Enrolment, enrolmentUPK> {
	
	
	public List<Enrolment> findByStudent(Student student);
		
	
	@Query("Select e.course from Enrolment e where e.student = :student")
	public List<Course> findCourseByStudent(Student student);

}

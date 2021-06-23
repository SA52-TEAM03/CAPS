package CA.CAPS.service;

import java.util.List;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;

public interface EnrolmentService {
	
	public void save(Enrolment enrolment);
	
	public List<Enrolment> findByStudent(Student student);
	
	public List<Course> findCourseByStudent(Student student);

}

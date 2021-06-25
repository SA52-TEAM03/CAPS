package CA.CAPS.service;

import java.util.List;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;

public interface EnrolmentService {

	public void save(Enrolment enrolment);

	public List<Enrolment> findByStudent(Student student);

	public List<Course> findCourseByStudent(Student student);

	public List<Student> findStudentsByCourse(int id);

	public List<Integer> findGradesByCourse(int id);

	public List<Enrolment> findEnrolByStudent(int id);

	public List<Integer> findGradeByStudent(int id);
}

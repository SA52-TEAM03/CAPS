package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.EnrolmentRepository;

@Service
public class EnrolmentServiceImpl implements EnrolmentService{
	
	@Autowired
	EnrolmentRepository enrolmentRepo;

	@Override
	public void save(Enrolment enrolment) {
		
		enrolmentRepo.save(enrolment);
		
	}

	@Override
	public List<Enrolment> findByStudent(Student student) {
		
		return enrolmentRepo.findByStudent(student);
	}

	@Override
	public List<Course> findCourseByStudent(Student student) {
		
		return enrolmentRepo.findCourseByStudent(student);
	}
	
	@Override
	public List<Student> findStudentsByCourse(int id) {
		return enrolmentRepo.findStudentIdbyCourseId(id);
	}

	@Override
	public List<Integer> findGradesByCourse(int id) {
		return enrolmentRepo.findMarksByCourseId(id);
	}

	@Override
	public List<Enrolment> findEnrolByStudent(int id) {
		return enrolmentRepo.findEnrolByStudentId(id);
	}

	@Override
	public List<Integer> findGradeByStudent(int id) {
		return enrolmentRepo.findMarksByStudentId(id);
	}
	
	@Override
	public Enrolment findByStudentCourse(Student student, Course course) {
		return enrolmentRepo.findEnrolByStudentCourse(student, course);
	}
}

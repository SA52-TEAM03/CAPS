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
	

}

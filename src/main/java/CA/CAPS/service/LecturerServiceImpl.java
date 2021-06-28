package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.CourseRepository;
import CA.CAPS.repo.EnrolmentRepository;
import CA.CAPS.repo.LecturerRepository;
import CA.CAPS.repo.StudentRepository;

@Service
public class LecturerServiceImpl implements LecturerService {

	@Autowired
	LecturerRepository lecturerRepo;
	
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	EnrolmentRepository enrolmentRepo;
	
	@Autowired
	StudentRepository studentRepo;

	@Override
	public Lecturer findLecturer(int id) {
		return lecturerRepo.getById(id);
	}

	@Override
	public Lecturer findLecturerByUserName(String userName) {
		return lecturerRepo.findUserByUserName(userName);
	}
	
	@Override
	public List<Course> findLecturerCourses(int id) {
		return courseRepo.findCoursesByLecturerId(id);
	}
	
	@Override
	public List<Student> findStudentsByCourse(int id) {
		return enrolmentRepo.findStudentIdbyCourseId(id);
	}

	@Override
	public Course findById(Integer id) {
		return courseRepo.findById(id).get();
	}
	
	@Override
	public List<Integer> findGradesByCourse(int id) {
		return enrolmentRepo.findMarksByCourseId(id);
	}
	
	@Override
	public Student getById(Integer id) {
		return studentRepo.getById(id);
	}
	
	@Override
	public List<Enrolment> findEnrolByStudent(int id) {
		return enrolmentRepo.findEnrolByStudentId(id);
	}
	
	@Override
	public List<Course> listAllCourses() {
		return courseRepo.findAll();
	}
	
	@Override
	public List<Integer> findGradeByStudent(int id) {
		return enrolmentRepo.findMarksByStudentId(id);
	}
	
	@Override
	public void save(Enrolment enrolment) {	
		enrolmentRepo.save(enrolment);	
	}
}

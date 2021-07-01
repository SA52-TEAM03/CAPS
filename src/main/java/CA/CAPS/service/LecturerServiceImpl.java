package CA.CAPS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.CourseRepository;
import CA.CAPS.repo.EnrolmentRepository;
import CA.CAPS.repo.LecturerRepository;
import CA.CAPS.repo.StudentRepository;
import CA.CAPS.util.GradeCount;

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
		return enrolmentRepo.findStudentsbyCourseId(id);
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

	@Override
	public List<GradeCount> getDataPoints(int id) {
		
		List<GradeCount> gradeCounts = new ArrayList<>();
		
		List<Integer> grades = findGradesByCourse(id);	
		List<Integer> gradeList = new ArrayList<>();
		List<Integer> gradeNum = new ArrayList<>();
		
		for(int grade : grades) {
			if(gradeList.contains(grade)){
				int index = gradeList.indexOf(grade);
				int count = gradeNum.get(index);
				count++;
				gradeNum.add(index, count);
			}
			else {
				gradeList.add(grade);
				gradeNum.add(1);
			}
		}
		
		for(int i = 0; i < gradeList.size(); i++) {
			GradeCount gradeCount = new GradeCount();
			gradeCount.setX(gradeList.get(i));
			gradeCount.setY(gradeNum.get(i));
			gradeCounts.add(gradeCount);
		}
		return gradeCounts;
	}
	
	@Override
	public Page<Course> coursePageForLecturer(Pageable pageable, Lecturer lecturer){
		return courseRepo.findByLecturer(pageable, lecturer);
	}

	@Override
	public Page<Student> enrolmentPageForLecturer(Pageable pageable, Course course) {
		return studentRepo.findByCourse(pageable, course);
	}

	@Override
	public Page<Integer> marksList(Pageable pageable, Course course) {
		return enrolmentRepo.findByMarks(pageable, course);
	}
}

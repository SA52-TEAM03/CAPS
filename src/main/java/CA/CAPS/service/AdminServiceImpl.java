package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Course;
import CA.CAPS.domain.Enrolment;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.AdminRepository;
import CA.CAPS.repo.CourseRepository;
import CA.CAPS.repo.EnrolmentRepository;
import CA.CAPS.repo.LecturerRepository;
import CA.CAPS.repo.StudentRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private LecturerRepository lecturerRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private EnrolmentRepository enrolmentRepo;

	@Override
	public void deleteLecturer(Lecturer lecturer) {
		lecturerRepo.delete(lecturer);
	}

	@Override
	public List<Lecturer> listAllLecturers() {
		return lecturerRepo.findAll();
	}

	@Override
	public Lecturer findLecturerByUserName(String name) {
		return lecturerRepo.findLecturerByUserName(name);
	}

	@Override
	public Lecturer findLecturerById(Integer id) {
		return lecturerRepo.findById(id).get();
	}

	@Override
	public Boolean isLecturerExist(Lecturer lecturer) {			
		for (Lecturer l : lecturerRepo.findAll()) {
			if (l.getId() == lecturer.getId())
				continue;
			if (l.getUserName().equalsIgnoreCase(lecturer.getUserName()))
				return true;
		}
		for (Student s : studentRepo.findAll()) {
			if (s.getUserName().equalsIgnoreCase(lecturer.getUserName()))
				return true;
		}
		for (Admin a : adminRepo.findAll()) {
			if (a.getUserName().equalsIgnoreCase(lecturer.getUserName()))
				return true;
		}
		return false;
	}

	@Override
	public void removeLecturerFromCourses(Lecturer lecturer) {
		for (Course c : lecturer.getCourses())
			c.setLecturer(null);
	}
	
	@Override
	public Page<Lecturer> lecturerPage(Pageable pageable){
		return lecturerRepo.findAll(pageable);
	}

	@Override
	public void saveCourse(Course course) {
		courseRepo.save(course);
	}

	@Override
	public void deleteCourse(Course course) {
		courseRepo.delete(course);
	}

	@Override
	public List<Course> listAllCourses() {
		return courseRepo.findAll();
	}

	@Override
	public Course findCourseById(Integer id) {
		return courseRepo.findById(id).get();
	}

	@Override
	public Boolean isCourseCodeExist(Course course) {
		for (Course c : courseRepo.findAll()) {
			if (c.getId() == course.getId())
				continue;
			if (c.getCode().equalsIgnoreCase(course.getCode()))
				return true;
		}
		return false;
	}
	
	@Override
	public Page<Course> coursePage(Pageable pageable){
		return courseRepo.findAll(pageable);
	}

	@Override
	public void deleteStudent(Student student) {
		studentRepo.delete(student);
	}

	@Override
	public List<Student> listStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student findStudentById(Integer id) {
		return studentRepo.findById(id).get();
	}
	
	@Override
	public Boolean isStudentExist(Student student) {
		for (Student s : studentRepo.findAll()) {
			if (s.getId() == student.getId())
				continue;
			if (s.getUserName().equalsIgnoreCase(student.getUserName()))
				return true;
		}
		for (Lecturer l : lecturerRepo.findAll()) {
			if(l.getUserName().equalsIgnoreCase(student.getUserName()))
				return true;
		}
		for (Admin a : adminRepo.findAll()) {
			if (a.getUserName().equalsIgnoreCase(student.getUserName()))
				return true;
		}
		return false;
	}
	
	@Override
	public Page<Student> studentPage(Pageable pageable){
		return studentRepo.findAll(pageable);
	}

	@Override
	public void saveEnrolment(Enrolment enrolment) {
		enrolmentRepo.save(enrolment);
	}

	@Override
	public void deleteEnrolment(Enrolment enrolment) {
		enrolmentRepo.delete(enrolment);
	}

	@Override
	public List<Enrolment> listAllEnrolments() {
		return enrolmentRepo.findAll();
	}

	@Override
	public List<Enrolment> findEnrolmentByStudentId(Integer id) {
		List<Enrolment> enrolment = enrolmentRepo.findEnrolByStudentId(id);
		return enrolment;
	}

	@Override
	public List<Student> findNotEnrolStudentsByCourseId(Integer id){
		
		List<Student> student = enrolmentRepo.findStudentsbyCourseId(id);
		if (student.isEmpty()) {
			return studentRepo.findAll();
		}
		
		return studentRepo.findStudentsNotIn(enrolmentRepo.findStudentsbyCourseId(id));
		
	}

	@Override
	public List<Student> findEnrolStudentsByCourseId(Integer id){
		return enrolmentRepo.findStudentsbyCourseId(id);
	}

	@Override
	public void enrollStudentsInCourse(List<Student> students, int courseId) {
		Course course = courseRepo.getById(courseId);
		for (Student s : students) {
			Enrolment enrolment = new Enrolment(s, course);
			if (course.getSize() > 0) {
				enrolmentRepo.save(enrolment);
			}
		}
	}

}

package CA.CAPS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
	public void saveLecturer(Lecturer lecturer) {
		lecturerRepo.save(lecturer);
	}

	@Override
	public void deleteLecturer(Lecturer lecturer) {
		lecturerRepo.delete(lecturer);
	}
	
	@Override
	public List<Lecturer> listAllLecturers() {
		return lecturerRepo.findAll();
	}	
	
	@Override
    public List<Lecturer> listAllLecturers(Pageable pageable){
 
        Page<Lecturer> pagedResult = lecturerRepo.findAll(pageable);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Lecturer>();
        }
    }
	
	@Override
	public Page<Lecturer> findLecturerPaginated(Pageable pageable){
		return new PageImpl<Lecturer>(listAllLecturers(pageable), pageable, lecturerRepo.findAll().size());
	}

	@Override
	public Lecturer findByUserName(String name) {
		return lecturerRepo.findLecturerByUserName(name);
	}
	
	@Override
	public Lecturer findLecturerById(Integer id) {
		return lecturerRepo.findById(id).get();
	}
	
	@Override
	public Boolean isUserNameExist(Lecturer lecturer) {
		for (Lecturer l : lecturerRepo.findAll()) {
			if(l.getId()==lecturer.getId())
				continue;
			if(l.getUserName().equalsIgnoreCase(lecturer.getUserName()))
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
		for(Course c : lecturer.getCourses())
			c.setLecturer(null);
	}
	
	@Override
	public void saveCourse(Course course) {
		courseRepo.save(course);
	}

	@Override
	public void updateCourse(Course course) {
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
    public List<Course> listAllCourses(Pageable pageable){
 
        Page<Course> pagedResult = courseRepo.findAll(pageable);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Course>();
        }
    }
	
	@Override
	public Page<Course> findCoursePaginated(Pageable pageable){
		return new PageImpl<Course>(listAllCourses(pageable), pageable, courseRepo.findAll().size());
	}
	
	@Override
	public Course findCourseById(Integer id) {
		return courseRepo.findById(id).get();
	}
	
	@Override
	public Course findByCode(String code) {
		return courseRepo.findCourseByCode(code);
	}

	@Override
	public Boolean isCourseCodeExist(Course course) {
		for (Course c : courseRepo.findAll()) {
			if (c.getId()==course.getId())
				continue;
			if (c.getCode().equalsIgnoreCase(course.getCode()))
				return true;
		}
		return false;
	}
	
	
	@Override
	public void saveStudent(Student student) {
		studentRepo.save(student);
	}

	@Override
	public void updateStudent(Student student) {
		studentRepo.save(student);
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
    public List<Student> listStudents(Pageable pageable){
 
        Page<Student> pagedResult = studentRepo.findAll(pageable);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Student>();
        }
    }
	
	@Override
	public Page<Student> findStudentPaginated(Pageable pageable){
		return new PageImpl<Student>(listStudents(pageable), pageable, studentRepo.findAll().size());
	}
	
	@Override
	public Boolean isStudentExist(Student student) {
		for (Student s : studentRepo.findAll()) {
			if (s.getId()==student.getId())
				continue;
			if (s.getUserName().equalsIgnoreCase(student.getUserName()))
				return true;
		}
		return false;
	}
	
	@Override
	public Student findStudentById(Integer id) {
		return studentRepo.findById(id).get();
	}
	
	@Override
	public void saveEnrolment(Enrolment enrolment) {		
		enrolmentRepo.save(enrolment);
	}
	
	@Override
	public void updateEnrolment(Enrolment enrolment) {
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
    public List<Enrolment> listAllEnrolments(Pageable pageable){
 
        Page<Enrolment> pagedResult = enrolmentRepo.findAll(pageable);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Enrolment>();
        }
    }
	
	
	@Override
	public Page<Enrolment> findEnrolmentPaginated(Pageable pageable){
		return new PageImpl<Enrolment>(listAllEnrolments(pageable), pageable, enrolmentRepo.findAll().size());
	}
	
	@Override
	public List<Enrolment> findEnrolmentByStudentId(Integer id){
		List<Enrolment> enrolment = enrolmentRepo.findEnrolByStudentId(id);
		return enrolment;
	}
	
	@Override
	public Boolean isEnrolmentExist(Enrolment enrolment) {
		for (Enrolment e : enrolmentRepo.findAll()) {
			if (e.getStudent()==enrolment.getStudent() && e.getCourse()==enrolment.getCourse())
				continue;
			if (e.getStudent().getUserName().equals(enrolment.getStudent().getUserName()))
				return true;
		}
		return false;
	}
	
	@Override
	public List<Student> findNotEnrolStudentsByCourseId(Integer id){
		
		List<Student> student = enrolmentRepo.findStudentIdbyCourseId(id);
		if (student.isEmpty()) {
			return studentRepo.findAll();
		}
		
		return studentRepo.findStudentsNotIn(enrolmentRepo.findStudentIdbyCourseId(id));
		
	}
	
	@Override
	public List<Student> findEnrolStudentsByCourseId(Integer id){
		return studentRepo.findStudentsIn(enrolmentRepo.findStudentIdbyCourseId(id));
	}
	
	@Override
	public void enrollStudentsInCourse(List<Student> students, int courseId) {
		for (Student s : students) {
			Enrolment enrolment = new Enrolment(s, courseRepo.getById(courseId));
			enrolmentRepo.save(enrolment);
		}
	}
	
	@Override
	public void updateCourseSize(int courseId) {
		Course course = courseRepo.findCourseById(courseId);
		Integer courseSize = course.getSize();
		try {
		if (courseSize > 0) {
			course.setSize( courseSize - 1);	
		} 
		} catch (Exception e) {
			
			throw e;
		}
		courseRepo.save(course);
	}
	
	@Override
	public void ReturnCourseSize(int courseId) {
		Course course = courseRepo.findCourseById(courseId);
		Integer courseSize = course.getSize();
		try {
		if (courseSize >= 0) {
			course.setSize( courseSize + 1);	
		} 
		} catch (Exception e) {
			
			throw e;
		}
		courseRepo.save(course);
	}

}

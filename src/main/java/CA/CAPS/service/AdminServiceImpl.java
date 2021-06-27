package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.repo.CourseRepository;
import CA.CAPS.repo.LecturerRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private LecturerRepository lecturerRepo;
	
	@Autowired
	private CourseRepository courseRepo;

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
			if(l.getUserName().equals(lecturer.getUserName()))
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
	public List<Course> listAllCoursesOrderByCode(){
		return courseRepo.listAllCourseOrderByCode();
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
	public Boolean isCodeExist(Course course) {
		for (Course c : courseRepo.findAll()) {
			if (c.getId()==course.getId())
				continue;
			if (c.getCode().equalsIgnoreCase(course.getCode()))
				return true;
		}
		return false;
	}
	
}

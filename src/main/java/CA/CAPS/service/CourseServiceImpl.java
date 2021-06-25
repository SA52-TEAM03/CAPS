package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Course;
import CA.CAPS.repo.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepo;

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
	public Course findById(Integer id) {
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

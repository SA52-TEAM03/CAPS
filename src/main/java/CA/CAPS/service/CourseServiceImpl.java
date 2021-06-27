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
	public List<Course> listAllCourses() {
		return courseRepo.findAll();
	}
	
	@Override
	public List<Course> findLecturerCourses(int id) {
		return courseRepo.findCoursesByLecturerId(id);
	}
	
	@Override
	public Course findById(Integer id) {
		return courseRepo.findById(id).get();
	}


}

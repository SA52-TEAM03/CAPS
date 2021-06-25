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
	public List<Course> findAll() {

		return courseRepo.findAll();
	}

	@Override
	public void save(Course course) {

		courseRepo.save(course);

	}

	@Override
	public Course getById(Integer id) {
		return courseRepo.getById(id);
	}

	@Override
	public List<Course> findLecturerCourses(int id) {
		return courseRepo.findCoursesByLecturerId(id);
	}

}

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
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.AdminRepository;
import CA.CAPS.repo.CourseRepository;
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
			if(l.getUserName().equals(lecturer.getUserName()))
				return true;
		}
		for (Student s : studentRepo.findAll()) {
			if (s.getUserName().equals(lecturer.getUserName()))
				return true;
		}
		for (Admin a : adminRepo.findAll()) {
			if (a.getUserName().equals(lecturer.getUserName()))
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
	
}

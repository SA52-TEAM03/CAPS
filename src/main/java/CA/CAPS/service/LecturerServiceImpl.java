package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Course;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.repo.LecturerRepository;

@Service
public class LecturerServiceImpl implements LecturerService{

	@Autowired
	LecturerRepository lecturerRepo;
	
	@Override
	public Lecturer findLecturer(int id) {
		return lecturerRepo.getById(id);
	}

	@Override
	public Lecturer findLecturerByUserName(String userName) {
		return lecturerRepo.findUserByUserName(userName);
	}
	

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
	public Lecturer findById(Integer id) {
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
	
}

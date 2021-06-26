package CA.CAPS.service;

import java.util.List;

import CA.CAPS.domain.Lecturer;

public interface LecturerService {

	public Lecturer findLecturer(int id);
	
	public boolean authenticateLecturer(String userName, String password);
	
	public Lecturer findLecturerByUserName(String userName);
	public void saveLecturer(Lecturer lecturer);
	public void deleteLecturer(Lecturer lecturer);
	public List<Lecturer> listAllLecturers();
	public Lecturer findByUserName(String name);
	public Lecturer findById(Integer id);
	public Boolean isUserNameExist(Lecturer lecturer);
	public void removeLecturerFromCourses(Lecturer Lecturer);
	
}

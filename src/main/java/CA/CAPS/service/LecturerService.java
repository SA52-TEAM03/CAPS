package CA.CAPS.service;

import CA.CAPS.domain.Lecturer;

public interface LecturerService {

	public Lecturer findLecturer(int id);
	
	public boolean authenticateLecturer(String userName, String password);
	
	public Lecturer findLecturerByUserName(String userName);
}

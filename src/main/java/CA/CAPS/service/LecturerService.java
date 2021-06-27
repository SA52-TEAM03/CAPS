package CA.CAPS.service;

import CA.CAPS.domain.Lecturer;

public interface LecturerService {

	public Lecturer findLecturer(int id);

	public Lecturer findLecturerByUserName(String userName);

}

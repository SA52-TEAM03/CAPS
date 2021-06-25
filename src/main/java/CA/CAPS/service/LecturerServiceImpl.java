package CA.CAPS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Lecturer;
import CA.CAPS.repo.LecturerRepository;

@Service
public class LecturerServiceImpl implements LecturerService{

	@Autowired
	LecturerRepository lrepo;
	
	@Override
	public Lecturer findLecturer(int id) {
		return lrepo.getById(id);
	}

	@Override
	public boolean authenticateLecturer(String userName, String password) {
		
		Lecturer lecturer = lrepo.findUserByUserNameAndPassword(userName, password);
		if(lecturer != null) {
			return true;
		}
		return false;
	}

	@Override
	public Lecturer findLecturerByUserName(String userName) {
		return lrepo.findUserByUserName(userName);
	}
}

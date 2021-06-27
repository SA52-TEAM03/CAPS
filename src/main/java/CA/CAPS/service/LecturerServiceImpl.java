package CA.CAPS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Lecturer;
import CA.CAPS.repo.LecturerRepository;

@Service
public class LecturerServiceImpl implements LecturerService {

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

}

package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.LecturerRepository;
import CA.CAPS.repo.StudentRepository;


@Service
public class UserImplementation implements UserService {

	@Autowired
	StudentRepository srepo;	
	
	@Autowired
	LecturerRepository lrepo;

	@Override
	public void createStudent(Student student) {
		srepo.save(student);
	}

	@Override
	public void updateStudent(Student student) {
		srepo.save(student);
	}

	@Override
	public List<Student> listAllStudent() {
		return srepo.findAll();
	}

	@Override
	public void deleteStudent(Student student) {
		srepo.delete(student);

	}

	@Override
	public boolean authenticate(Student student) {
		Student fromDB = srepo.findUserByUserNameAndPassword(student.getUserName(), student.getPassword());
		if (fromDB.getUserName().equals(student.getUserName()) && fromDB.getPassword().equals(student.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public Student findByStudentUserName(String userName) {
   		return srepo.findUserByUserName(userName);
	}

	@Override
	public void createLecturer(Lecturer lecturer) {
		lrepo.save(lecturer);
	}

	@Override
	public void updateLecturer(Lecturer lecturer) {
		lrepo.save(lecturer);
	}

	@Override
	public List<Lecturer> listAllLecturer() {
		return lrepo.findAll();
	}

	@Override
	public void deleteLecturer(Lecturer lecturer) {
		lrepo.delete(lecturer);
	}

	@Override
	public boolean authenticate(Lecturer lecturer) {
		Lecturer fromDB = lrepo.findUserByUserNameAndPassword(lecturer.getUserName(), lecturer.getPassword());
		if (fromDB.getUserName().equals(lecturer.getUserName()) && fromDB.getPassword().equals(lecturer.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public Lecturer findByLecturerUserName(String userName) {
   		return lrepo.findUserByUserName(userName);
	}
}

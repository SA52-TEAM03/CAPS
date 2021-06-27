package CA.CAPS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.AdminRepository;
import CA.CAPS.repo.LecturerRepository;
import CA.CAPS.repo.StudentRepository;

@Service
public class UserImplementation implements UserService {

	@Autowired
	StudentRepository srepo;

	@Autowired
	LecturerRepository lrepo;

	@Autowired
	AdminRepository arepo;

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
		Student fromDB = srepo.findUserByUserName(student.getUserName());
		if (fromDB != null && fromDB.getPassword().equals(student.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public Student findStudentByUserName(String userName) {
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
		Lecturer fromDB = lrepo.findUserByUserName(lecturer.getUserName());
		if (fromDB != null && fromDB.getPassword().equals(lecturer.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public Lecturer findLecturerByUserName(String userName) {
		return lrepo.findUserByUserName(userName);
	}

	@Override
	public boolean authenticate(Admin admin) {
		Admin fromDB = arepo.findUserByUserName(admin.getUserName());
		if (fromDB != null && fromDB.getPassword().equals(admin.getPassword()))
			return true;
		else
			return false;
	}

	@Override
	public Admin findAdminByUserName(String userName) {
		return arepo.findUserByUserName(userName);
	}

	@Override
	public void createAdmin(Admin admin) {
		arepo.save(admin);
	}

	@Override
	public Object findByUserName(String userName) {
		if (this.findAdminByUserName(userName) != null)
			return this.findAdminByUserName(userName);
		else if (this.findLecturerByUserName(userName) != null)
			return this.findLecturerByUserName(userName);
		else if (this.findStudentByUserName(userName) != null)
			return this.findStudentByUserName(userName);
		return null;
	}
}

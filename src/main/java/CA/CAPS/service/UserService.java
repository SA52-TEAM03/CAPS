package CA.CAPS.service;

import java.util.List;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;

public interface UserService {

	public void createStudent(Student student);
	public void updateStudent(Student student);
	public List<Student> listAllStudent();
	public void deleteStudent(Student student);
	public boolean authenticate(Student student);
	public Student findByStudentUserName(String userName);

	public void createLecturer(Lecturer lecturer);
	public void updateLecturer(Lecturer lecturer);
	public List<Lecturer> listAllLecturer();
	public void deleteLecturer(Lecturer lecturer);
	public boolean authenticate(Lecturer lecturer);
	public Lecturer findByLecturerUserName(String userName);

	public void createAdmin(Admin admin);
	public boolean authenticate(Admin admin);
	public Admin findByAdminUserName(String userName);

	public Object findByUserName(String userName);

}

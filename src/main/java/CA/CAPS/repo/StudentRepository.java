package CA.CAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import CA.CAPS.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findUserByUserNameAndPassword(String userName, String password);
    public Student findUserByUserName(String userName);
}

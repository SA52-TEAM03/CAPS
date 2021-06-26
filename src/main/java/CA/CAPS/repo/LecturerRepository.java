package CA.CAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import CA.CAPS.domain.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
	
	public Lecturer findUserByUserNameAndPassword(String userName, String password);

	public Lecturer findUserByUserName(String userName);
	
	public Lecturer findLecturerByUserName(String userName);
	
}

package CA.CAPS.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import CA.CAPS.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	public Admin findUserByUserName(String userName);
	
	public Admin findById(int id);

}

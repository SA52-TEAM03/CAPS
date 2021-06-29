package CA.CAPS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import CA.CAPS.domain.Admin;
import CA.CAPS.domain.Lecturer;
import CA.CAPS.domain.Student;
import CA.CAPS.repo.AdminRepository;
import CA.CAPS.repo.LecturerRepository;
import CA.CAPS.repo.StudentRepository;

@Service
public class UserServiceImple implements UserDetailsService {

	@Autowired
	StudentRepository srepo;

	@Autowired
	LecturerRepository lrepo;

	@Autowired
	AdminRepository arepo;

	@Autowired
	UserDetailsImpl userDetails;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void createStudent(Student student) {
		student.setPassword(passwordEncoder.encode(student.getPassword()));
		srepo.save(student);
	}

	public void createLecturer(Lecturer lecturer) {
		lecturer.setPassword(passwordEncoder.encode(lecturer.getPassword()));
		lrepo.save(lecturer);
	}

	public void createAdmin(Admin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		arepo.save(admin);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Admin admin = arepo.findUserByUserName(username);
		Student student = srepo.findUserByUserName(username);
		Lecturer lecturer = lrepo.findUserByUserName(username);

		List<String> authorities = new ArrayList<>();
		
		if (admin != null) {
			userDetails.setUsername(username);
			userDetails.setPassword(admin.getPassword());
			authorities.add("admin");
		} else if (student != null) {
			userDetails.setUsername(username);
			userDetails.setPassword(student.getPassword());
			authorities.add("student");
		} else if (lecturer != null) {
			userDetails.setUsername(username);
			userDetails.setPassword(lecturer.getPassword());
			authorities.add("lecturer");
		} else {
			throw new UsernameNotFoundException("user not found");
		}
		userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities)));

		return userDetails;
	}
}

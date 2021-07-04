package CA.CAPS.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

@Entity
public class Lecturer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String staffId = "LEC" + RandomStringUtils.randomNumeric(5);

	@NotEmpty(message = "First Name is required.")
	@Pattern(regexp = "^[a-zA-Z ]*", message = "First Name must not contain numbers/special characters")
	private String firstName;
	
	@NotEmpty(message = "Last Name is required.")
	@Pattern(regexp = "^[a-zA-Z ]*", message = "Last Name must not contain numbers/special characters")
	private String lastName;
	
	@Column(unique = true)
	@NotEmpty(message = "Username is required.")
	private String userName;
	
	@NotEmpty(message = "Password is required.")
	private String password;
	
	@OneToMany(mappedBy = "lecturer", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Collection<Course> courses;

	public Lecturer(String firstName, String lastName, String userName, String password, Collection<Course> courses) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.courses = courses;
	}

	public Lecturer(String firstName, String lastName, String userName, String password) {
		this(firstName, lastName, userName, password, new ArrayList<Course>());
	}

	public Lecturer(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Lecturer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}

	public String getStaffId() {
		return staffId;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}

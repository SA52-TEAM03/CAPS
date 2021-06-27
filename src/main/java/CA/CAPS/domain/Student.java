package CA.CAPS.domain;

import java.time.LocalDate;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique = true)
	@NotEmpty(message = "Username is required.")
	private String userName;

	@NotEmpty(message = "Password is required.")
	private String password;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private Collection<Enrolment> enrolments;

	@NotEmpty(message = "First Name is required.")
	@Pattern(regexp = "^[a-zA-Z]*", message = "First Name must not contain numbers/special characters")
	private String firstName;

	@NotEmpty(message = "Last Name is required.")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Last Name must not contain numbers/special characters")
	private String lastName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate enrollmentDate;

	public Student(String userName, String password, Collection<Enrolment> enrolments, String firstName,
			String lastName, LocalDate enrollmentDate) {
		super();
		this.userName = userName;
		this.password = password;
		this.enrolments = enrolments;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enrollmentDate = enrollmentDate;
	}

	public Student(String userName, String password, String firstName, String lastName, LocalDate enrollmentDate) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enrollmentDate = enrollmentDate;
		this.enrolments = new ArrayList<Enrolment>();
	}

	public Student(String userName, String password, String firstName, String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.enrolments = new ArrayList<Enrolment>();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Student(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Collection<Enrolment> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(Collection<Enrolment> enrolments) {
		this.enrolments = enrolments;
	}

	public void addEnrolments(Course course) {
		Enrolment e = new Enrolment(this, course);
		this.enrolments.add(e);
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

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", enrollmentDate=" + enrollmentDate + "]";
	}

}

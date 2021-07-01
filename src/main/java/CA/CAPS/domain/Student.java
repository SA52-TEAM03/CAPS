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

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String matricNo = "STU" + RandomStringUtils.randomNumeric(5);

	@Column(unique = true)
	@NotEmpty(message = "Username is required.")
	private String userName;

	@NotEmpty(message = "Password is required.")
	private String password;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
	private Collection<Enrolment> enrolments;

	@NotEmpty(message = "First Name is required.")
	@Pattern(regexp = "^[a-zA-Z ]*", message = "First Name must not contain numbers/special characters")
	private String firstName;

	@NotEmpty(message = "Last Name is required.")
	@Pattern(regexp = "^[a-zA-Z ]*", message = "Last Name must not contain numbers/special characters")
	private String lastName;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate enrollmentDate;

	public Student(String userName, String password, String firstName,	String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enrollmentDate = LocalDate.now();
	}

	public Student(String userName, String password, String firstName, String lastName, LocalDate enrollmentDate) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enrollmentDate = enrollmentDate;
	}

	public Student(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Student() {
		super();
		this.enrolments = new ArrayList<Enrolment>();
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

	public String getMatricNo() {
		return matricNo;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

}

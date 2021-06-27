package CA.CAPS.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotEmpty(message = "First Name is required.")
	@Pattern(regexp = "^[a-zA-Z]*", message = "First Name must not contain numbers/special characters")
	private String firstName;

	@NotEmpty(message = "Last Name is required.")
	@Pattern(regexp = "^[a-zA-Z]*", message = "Last Name must not contain numbers/special characters")
	private String lastName;

	@Column(unique = true)
	@NotEmpty(message = "Username is required.")
	private String userName;

	@NotEmpty(message = "Password is required.")
	private String password;

	public Admin(String firstName, String lastName, String userName, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	public Admin(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Admin() {
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

}

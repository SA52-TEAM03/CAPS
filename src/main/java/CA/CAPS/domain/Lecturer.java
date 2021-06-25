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

@Entity
public class Lecturer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String userName;
	private String password;
	@OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
	private Collection<Course> courses;

	public Lecturer(String userName, String password, Collection<Course> courses) {
		super();
		this.userName = userName;
		this.password = password;
		this.courses = courses;
	}

	public Lecturer(String userName, String password) {
		this(userName, password, new ArrayList<Course>());
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

	@Override
	public String toString() {
		return "Lecturer [id=" + id + ", userName=" + userName + "]";
	}

}

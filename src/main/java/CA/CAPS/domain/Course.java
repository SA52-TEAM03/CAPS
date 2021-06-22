package CA.CAPS.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String name;
	private Integer size;
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Collection<Enrolment> enrolments;
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Lecturer lecturer;

	private Integer credit;

	public Course(String name, Integer size, Collection<Enrolment> enrolments, Lecturer lecturer, Integer credit) {
		super();
		this.name = name;
		this.size = size;
		this.enrolments = enrolments;
		this.lecturer = lecturer;
		this.credit = credit;
	}

	public Course(String name, Integer size, Integer credit) {
		this(name, size, new ArrayList<Enrolment>(), null, credit);
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Collection<Enrolment> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(Collection<Enrolment> enrolments) {
		this.enrolments = enrolments;
	}

	public void addEnrolments(Student student) {
		Enrolment e = new Enrolment(student, this);
		this.enrolments.add(e);
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", size=" + size + ", credit=" + credit + "]";
	}

}

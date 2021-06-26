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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(unique = true)
	@NotEmpty(message="Code is required")
	@Pattern(regexp="^[a-zA-Z0-9]*", message = "Code must not contain special characters")
	private String code;
	
	@NotEmpty(message="Name is required")
	private String name;
	
	@NotNull(message="Size is required")
	@Range(min = 0, max = 100)
	private Integer size;
	
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Collection<Enrolment> enrolments;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "lecturer_id", nullable = true)
	private Lecturer lecturer;
	
	@NotNull(message="Credit is required")
	@Range(min = 3, max = 8)
	private Integer credit;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	public Course(String code, String name, Integer size, Collection<Enrolment> enrolments, Lecturer lecturer, Integer credit, LocalDate startDate) {
		super();
		this.code = code;
		this.name = name;
		this.size = size;
		this.enrolments = enrolments;
		this.lecturer = lecturer;
		this.credit = credit;
		this.startDate = startDate;
	}

	public Course(String code, String name, Integer size, Integer credit, LocalDate startDate) {
		this(code, name, size, new ArrayList<Enrolment>(), null, credit, startDate);
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
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

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", size=" + size + ", credit=" + credit + "]";
	}

}

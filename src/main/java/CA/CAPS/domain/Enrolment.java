package CA.CAPS.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Range;


@Entity
@IdClass(enrolmentUPK.class)
public class Enrolment {
	@Id
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Student student;
	@Id
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Course course;
	@Range(min = 0, max = 100)
	private Integer marks;

	public Enrolment(Student student, Course course, Integer marks) {
		super();
		this.student = student;
		this.course = course;
		this.marks = marks;
	}

	public Enrolment(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}

	public Enrolment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getGrade() {
		return marks;
	}

	public void setGrade(Integer marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Enrolment [student=" + student.getUserName() + ", course=" + course.getName() + ", marks=" + marks
				+ "]";
	}

}

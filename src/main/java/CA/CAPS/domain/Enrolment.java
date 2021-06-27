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
	private Integer grade;

	public Enrolment(Student student, Course course, Integer grade) {
		super();
		this.student = student;
		this.course = course;
		this.grade = grade;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Enrolment [student=" + student.getUserName() + ", course=" + course.getName() + ", grade=" + grade
				+ "]";
	}

}

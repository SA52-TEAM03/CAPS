package CA.CAPS.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class enrolmentUPK implements Serializable {
	private Integer student;
	private Integer course;
	public enrolmentUPK(Integer student, Integer course) {
		super();
		this.student = student;
		this.course = course;
	}
	public enrolmentUPK() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getStudent() {
		return student;
	}

	public Integer getCourse() {
		return course;
	}
	
}

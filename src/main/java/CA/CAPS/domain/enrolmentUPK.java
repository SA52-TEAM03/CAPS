package CA.CAPS.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class enrolmentUPK implements Serializable {
	private Integer student;
	private Integer course;
}

package CA.CAPS.util;

public class Grade {
	
	private Double gradePoint;
	private String grade;
	
	public Grade (Double gradePoint, String grade) {			
		this.gradePoint = gradePoint;
		this.grade = grade;
	}
	
	public Double getGradePoint() {
		return gradePoint;
	}	

	public String getGrade() {
		return grade;
	}	

}

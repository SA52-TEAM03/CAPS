package CA.CAPS.service;

import CA.CAPS.domain.Student;

public interface StudentService {
		
	public void save(Student student);
	
	public Student getById(Integer id);
	
	public boolean checkCourseAvailability(Integer id);
	
	public void enrollCourse(int studentId, int courseId);

}

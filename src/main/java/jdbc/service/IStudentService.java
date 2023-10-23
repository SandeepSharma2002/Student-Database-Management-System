package jdbc.service;

import jdbc.dto.Student;

public interface IStudentService {
	public String addStudent(Student std);
	public Student searchStudent(Integer sid);
	public String updateStudent(Student student); 
	public String deleteStudent(Integer sid);
}

package jdbc.service;

import jdbc.daofactory.StudentDaoFactory;
import jdbc.dto.Student;
import jdbc.persistence.IStudentDao;

public class StudentServiceImpl implements IStudentService{

	private IStudentDao stdDao;
	@Override
	public String addStudent(Student student) {
		
		stdDao = StudentDaoFactory.getStudentDao();
		if(stdDao != null) return stdDao.addStudent(student);
		else return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		if(stdDao != null) return stdDao.searchStudent(sid);
		else return null;
	}

	@Override
	public String updateStudent(Student student) {
		stdDao = StudentDaoFactory.getStudentDao();
		if(stdDao != null) return stdDao.updateStudent(student);
		else return "Not Found";
		
	}

	@Override
	public String deleteStudent(Integer sid) {
		stdDao = StudentDaoFactory.getStudentDao();
		if(stdDao != null) return stdDao.deleteStudent(sid);
		else return "Not Found";
	}

}

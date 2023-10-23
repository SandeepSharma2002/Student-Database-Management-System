package jdbc.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jdbc.dto.Student;
import jdbc.util.JdbcUtility;

public class StudentDaoImpl implements IStudentDao {

	
	PreparedStatement pStatement = null;
	Connection connection =null;
	ResultSet resultSet = null;
	String sqlQuery = null;
	
	@Override
	public String addStudent(Student std) {
		
		try {
			connection = JdbcUtility.getJdbcConnection();
			sqlQuery = "insert into students (name,age,address) values (?,?,?)";
			if(connection != null)
			{
				pStatement = connection.prepareStatement(sqlQuery);
				pStatement.setString(1, std.getSname());
				pStatement.setInt(2, std.getSage());
				pStatement.setString(3, std.getSaddress());
				int result = pStatement.executeUpdate();
				if(result == 1)
					return "success";
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtility.closeup(connection, pStatement, resultSet);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		try {
			connection = JdbcUtility.getJdbcConnection();
			sqlQuery = "select * from students where id = ?";
			if(connection != null)
			{
				pStatement = connection.prepareStatement(sqlQuery);
				pStatement.setInt(1, sid);
				resultSet = pStatement.executeQuery();
				Student sdt = null;
				if(resultSet != null)
				{
					if(resultSet.next())
					{
						sdt = new Student();
						sdt.setSid(resultSet.getInt(1));
						sdt.setSname(resultSet.getString(2));
						sdt.setSage(resultSet.getInt(3));
						sdt.setSaddress(resultSet.getString(4));
					}
					return sdt;
				}
				
			 }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtility.closeup(connection, pStatement, resultSet);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
				return null;
	}

	@Override
	public String updateStudent(Student student) {
		try {
			connection = JdbcUtility.getJdbcConnection();
			sqlQuery = "UPDATE students SET name = ? , age = ?, address = ? WHERE id = ?";
			if(connection != null)
			{
				pStatement = connection.prepareStatement(sqlQuery);
				pStatement.setString(1,student.getSname());
				pStatement.setInt(2,student.getSage());
				pStatement.setString(3,student.getSaddress());
				pStatement.setInt(4,student.getSid());
				int result = pStatement.executeUpdate();
				if(result == 1)
					return "success";
				else if(result == 0)
					return "Not found";
				else 
					return "failed";
			 }
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtility.closeup(connection, pStatement, resultSet);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return "Failed";
		
	}

	@Override
	public String deleteStudent(Integer sid) {
		try {
			connection = JdbcUtility.getJdbcConnection();
			sqlQuery = "delete from students where id = ?";
			if(connection != null)
			{
				pStatement = connection.prepareStatement(sqlQuery);
				pStatement.setInt(1, sid);
				int result = pStatement.executeUpdate();
				if(result == 1)
					return "success";
				else if(result == 0)
					return "Not found";
				else 
					return "failed";
			 }
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				JdbcUtility.closeup(connection, pStatement, resultSet);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return "Failed";
		
	}

}

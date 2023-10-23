package jdbc.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtility {

    private JdbcUtility(){}
    static {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    @SuppressWarnings("resource")
	public static Connection getJdbcConnection() throws Exception
    {
    	System.out.println("Connetion Stating");
    	HikariConfig config = new HikariConfig("C:\\Users\\sande\\Documents\\Java Projects\\CRUDAPPJDBC\\src\\main\\java\\jdbc\\Properties\\data.properties");
    	HikariDataSource dataSource = new HikariDataSource(config);
    	System.out.println("Connetion Stated");
    	return dataSource.getConnection();
    }
    
//    Physical Connection
    
//    public static Connection getJdbcConnection() throws Exception
//    {
//        Connection connection = null;
//        
//        FileInputStream fis = new FileInputStream("D:/Doc/jdbcCRUD_APP/src/jdbc/Properties/data.properties");
//        Properties properties = new Properties();
//        properties.load(fis);  
//    
//        connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),properties.getProperty("password"));
//        return connection;
//    }

    public static void closeup(Connection connection, Statement statement, ResultSet resultSet ) throws SQLException
    {
        if(connection != null) connection.close();
        if (statement != null) statement.close();
        if(resultSet != null) resultSet.close();
        
    }
}


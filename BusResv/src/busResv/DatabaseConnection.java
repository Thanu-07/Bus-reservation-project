package busResv;
import java.sql.*;

public class DatabaseConnection {
	
	private static final String url = "jdbc:mysql://localhost:3306/JDBC";
	private static final String userName = "root";
	private static final String password = "9150";
	
	public static Connection getDatabaseConnection() throws SQLException{
		return DriverManager.getConnection(url,userName,password);
	}

}

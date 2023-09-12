package busResv;
import java.sql.*;

public class BusDatabaseAccessObject {
	
	public static void displayBusInfo() throws SQLException{
		
		String query = "select * from Bus";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			System.out.println("Bus Number is : " + rs.getInt(1));
			
			if (rs.getInt(2) == 1) {
				System.out.println("AC : " + " true");
			}
			else
				System.out.println("AC : " + " false");
			
			
			System.out.println("Capacity is : " + rs.getInt(3));
			System.out.println("Bus route : " + rs.getString(4));
			System.out.println();
		}
		
		System.out.println("--------------------------------------------");
		
	}
	
	public int getCapacity(int busNo) throws SQLException {
		
		String query = "select capacity from Bus where id = " + busNo;
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		return rs.getInt(1);
	}

}

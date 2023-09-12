package busResv;
import java.util.Date;
import java.sql.*;

public class BookingDAO {
	
	public int getBookedCount(int busNo,Date date) throws SQLException{
		
		String query = "select Count(*) from Booking where bus_no = ? and travel_date = ?";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(query);
		
		java.sql.Date sqldateFormat = new java.sql.Date(date.getTime());
		pst.setInt(1, busNo);
		pst.setDate(2, sqldateFormat);
		
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		return rs.getInt(1);
		
	}
	
	public void addBooking(PassengerBooking booking) throws SQLException {
		
		String query = "insert into Booking(passenger_name,bus_no,travel_date)values (?,?,?)";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(query);
		
		java.sql.Date sqldateFormat = new java.sql.Date(booking.date.getTime());
		
		pst.setString(1, booking.passengerName);
		pst.setInt(2, booking.busNo);
		pst.setDate(3, sqldateFormat);
		
		pst.executeUpdate();
		
	}
	

}

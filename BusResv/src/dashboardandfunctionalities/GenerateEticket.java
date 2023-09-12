package dashboardandfunctionalities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import busResv.DatabaseConnection;

public class GenerateEticket {

	
	void generateTicket (String bookNo) throws SQLException{
		
		
		String query = "select booking_no,passenger_name,age,gender,bus_No,bus_Type,seat_No,boarding_point,concat (date(travel_date_time),' | ',time(travel_date_time)),dropping_point,concat (date(arriving_date_time),' | ',time(arriving_date_time))  from booking_records where booking_no = " +bookNo+ "";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		System.out.println("Ticket Information : \n\n-----------------------------------------------------------------------------------------------------");
		
		while(rs.next()) {
			// System.out.println("Booking ID : " + rs.getString(1) + "                    Bus : " + rs.getInt(5) + "                    Bus Type : " + rs.getInt(6)+ "\n" + "Boarding Point : " + rs.getString(8) + "\nJourney Date and Time : " + rs.getString(9) + "                    Dropping Point : " + rs.getString(10) + "\nDropping Date and Time : " + rs.getString(11) + "\n Passenger Details : " + rs.getString(2) + " | Age : " + rs.getInt(3) + " | Gender : " + rs.getString(4) + "\n Seat No : " + rs.getInt(7));
			
			System.out.println("\n Booking ID : " + rs.getString(1) + "            Bus : " + rs.getInt(5) + "            Bus Type : " + rs.getString(6) + "\n\n-----------------------------------------------------------------------------------------------------\n\n Boarding Point : " + rs.getString(8) + "                         |     Dropping Point : " + rs.getString(10) + "\n\n Journey Date and Time : " + rs.getString(9)  + "    |     Dropping Date and Time : " + rs.getString(11) + "\n\n-----------------------------------------------------------------------------------------------------\n\n Passenger Details : " + rs.getString(2) + " | Age : " + rs.getInt(3) + " | Gender : " + rs.getString(4) + "\n Seat No           : " + rs.getInt(7));
			System.out.println();
		}
		
	}

	public void generateTicket(ArrayList<String> booknos) throws SQLException {
		
		String query = "select booking_no,passenger_name,age,gender,bus_No,bus_Type,seat_No,boarding_point,concat (date(travel_date_time),' | ',time(travel_date_time)),dropping_point,concat (date(arriving_date_time),' | ',time(arriving_date_time))  from booking_records where booking_no in (" +String.join(",", booknos)+ ")";
		
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		System.out.println("Ticket Information : \n\n-----------------------------------------------------------------------------------------------------");
		
		while(rs.next()) {
			// System.out.println("Booking ID : " + rs.getString(1) + "                    Bus : " + rs.getInt(5) + "                    Bus Type : " + rs.getInt(6)+ "\n" + "Boarding Point : " + rs.getString(8) + "\nJourney Date and Time : " + rs.getString(9) + "                    Dropping Point : " + rs.getString(10) + "\nDropping Date and Time : " + rs.getString(11) + "\n Passenger Details : " + rs.getString(2) + " | Age : " + rs.getInt(3) + " | Gender : " + rs.getString(4) + "\n Seat No : " + rs.getInt(7));
			
			System.out.println("\n Booking ID : " + rs.getString(1) + "            Bus : " + rs.getInt(5) + "            Bus Type : " + rs.getString(6) + "\n\n-----------------------------------------------------------------------------------------------------\n\n Boarding Point : " + rs.getString(8) + "                         |     Dropping Point : " + rs.getString(10) + "\n\n Journey Date and Time : " + rs.getString(9)  + "    |     Dropping Date and Time : " + rs.getString(11) + "\n\n-----------------------------------------------------------------------------------------------------\n\n Passenger Details : " + rs.getString(2) + " | Age : " + rs.getInt(3) + " | Gender : " + rs.getString(4) + "\n Seat No           : " + rs.getInt(7));
			
		}
		
	}
		
		
	

//	public void getTicket(ArrayList<String> booknos) throws SQLException {
//		
//		for(int i=0;i<booknos.size();i++) {
//			generateTicket (booknos.get(i));
//		}
		
		
	
	
	
	
}

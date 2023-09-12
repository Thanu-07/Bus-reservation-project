package Admindashboardfunctionalities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import busResv.DatabaseConnection;
import dashboardandfunctionalities.End_UserMenu;
import dashboardandfunctionalities.UserBooking;

public class AdminFunction {
	
	
	 int busNo;
	 String type;
	 int capacity;
	 String from;
	 String to;
	
	
//	public static void main (String[] args) {
//		Scanner s = new Scanner(System.in);
//		
//		 try {
//			updateBusSchedule(s,viewSchedule());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}	
	
	
	

	
	 int  GetBus(Scanner s) throws SQLException{
		
		ArrayList<Integer> ar = displayBuses();
		 
		 
	    System.out.println("Available Buses : ");
	    for(Integer i : ar) {
	    	System.out.println(i);
	    	
	    }
		
	    int busid;
		
		while (true) {
			System.out.println("Enter Bus to Update :");

			try {
				busNo = s.nextInt();
				if (ar.contains(busNo)) {
					 busid = getBusID(busNo);
					break;
				}
				else
					System.out.println("Invalid Bus Number...");

			} catch (Exception e) {
				System.out.println(e);
				s.nextLine();
			}
		}
		
		return busid;
	}
		
	
		


	
	
	static ArrayList<Integer> viewSchedule() throws SQLException {
		 
			ArrayList<Integer> arr = new ArrayList<>();
			
		 
		
		String query  = "select schedule_id,date(schedule_operating_date_time) as travel_date,time(schedule_operating_date_time) as travel_time,date(schedule_reaching_date_time) as arriving_date,time(schedule_reaching_date_time) as arriving_time from schedule_of_bus";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			System.out.println("Schedule ID : " + rs.getInt(1) + "| Travel Date : " + rs.getString(2) + "| Travel Time : " + rs.getString(3) + "| Arriving Date : " + rs.getString(4) +"| Arriving Time : " + rs.getString(5));
			 arr.add(rs.getInt(1));
		}
		
		return arr;
		
	}

	
	

	static void updateBusScheduleDAO(Scanner s, int id) throws SQLException {
		
		
		String query = "update schedule_of_bus set schedule_operating_date_time = ? ,schedule_reaching_date_time= ? where schedule_id = ?";
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement st = con.prepareStatement(query);
		
		
		System.out.println("Enter operating date with time : ");
		String Operdt = s.nextLine();
		
		
		st.setString(1, query);
		st.setString(2, query);
		st.setInt(3, id);
		int rows = st.executeUpdate();
		
		
	
	
	}
	
	
	static void updateBusSchedule(Scanner s,ArrayList<Integer> arr) {
		
		
		
		
		while(true) {
			System.out.println("Enter schedule id : ");
		try {
			
		int id = s.nextInt();
		
		if(arr.contains(id)) {
			updateBusScheduleDAO(s,id);
			break;
			
		}
		
		}
		catch(Exception e) {
			System.out.println(e);
			s.nextLine();
		}
	}
	}
	
	

	
	static void showUser() throws SQLException {
		 // to see the list of registered users and their details
		String query = "select * from User_Info";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		System.out.println("|   User_ID   |     Email_ID     |   Password   |    User_Name    |" );
		
		while(rs.next()) {
			System.out.println(rs.getInt(1) + "" + rs.getString(2) + "" + rs.getString(3) + "" + rs.getString(4)); 
		}
		
	}
	
	static void checkUser(Scanner s) throws SQLException {
		
		
		ArrayList<Integer> arr = getUser();
		
		System.out.println("User ID : ");
		
		for(Integer i : arr) {
			System.out.println(i);
			
		}
		
		
		while(true) {
		
		System.out.println("Enter user id : ");
		
		try {
		
		int userid = s.nextInt();
		
		if(arr.contains(userid)) {
			viewUser(userid);
			break;
			
		}
		else
			System.out.println("User not found....");
		
		}
		catch(Exception e) {
			System.out.println(e);
			s.nextLine();
		}
	}
		
	}	
		
	private static void viewUser(int userid) throws SQLException {
		
		String query = "select * from User_Info where id = " + userid + "";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			
			System.out.println("User ID : " + rs.getInt(1) + "| Email Address : " + rs.getString(2) + "| Password : " + rs.getString(3) + "| User Name : " + rs.getString(4));
			
		}
		
	}





	static ArrayList<Integer> getUser() throws SQLException{
		
		ArrayList<Integer> ar = new ArrayList<Integer>();
		
		String query = "select id from User_Info";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			
			ar.add(rs.getInt(1));
			
		}
		
		return ar;
	
	}
	
	
	
	
	
	
	static void cancelTicket() {
		
	}
	
	
	
	
	static void seeBookinghistory() {
		
	}
	
	static void forgetPassword() {
		
	}
	
	
	
	static int getBusID(int busNo) throws SQLException{
		
		String getBus = "select id from bus_list where bus_No = " + busNo +"";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(getBus);
		
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
		
		
		
	}
	
	
	static ArrayList<Integer> displayBuses() throws SQLException{
		
		ArrayList<Integer> ar = new ArrayList<>();
		
		String buses;
	
		String getBus = "select bus_No from bus_list ";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(getBus);
		
		while(rs.next()) {
			ar.add(rs.getInt(1));
		}
		
		return ar;
		
	}

	
	
public static ArrayList <String> BookingHistory(String user) {
		
		ArrayList <String> bookhis = new ArrayList<>();
		
		String query = "select * from booking_records where User_name = ' " + user + "'";
		
		try(	Connection con = DatabaseConnection.getDatabaseConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);){
			
			int i = 1;
			while(rs.next()) {
			 
				bookhis.add(i++ +") " + "Booking Number : " + rs.getString(2) + " | " + "Passenger Name : " + rs.getString(4) + " | " + "Age : " + rs.getInt(5) + " | " + "Gender : " + rs.getString(6) + " | " + "Bus Number : " + rs.getInt(7) + " | " + "Bus Type : " + rs.getString(8) + " | " + "Seat Number : " + rs.getInt(9) + " | " + "Boarding Point : " + rs.getString(10) + " | " + "Travel Date & Time : " + rs.getString(11) + " | " + "Dropping Point : " + rs.getString(12) + " | " +"Arriving Date & Time : " + rs.getString(13));
			
		}
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return bookhis;
		
	}
	
	
	public static String BookingCancellation(String bookno){
		
		
	
		
		String query = "select booking_no from booking_records where booking_no = '" +bookno +"'";
		
		
		try(	Connection con = DatabaseConnection.getDatabaseConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);){
			
			int i = 1;
			while(rs.next()) {
				
				String bookingno = rs.getString(1);
						
				getBookNoID(rs.getString(1));
				
					return bookingno;
				
		
				
			}
		
		
	}
		catch(Exception e ) {
			e.printStackTrace();
		}
		
		return "";
	
	}
	
	






	public static void requestCancelTicket(int booknoid) {
		
		String query = "delete from booking_records where record = " + booknoid;
		
		try(	Connection con = DatabaseConnection.getDatabaseConnection();
				Statement st = con.createStatement();){
			
			int rows = st.executeUpdate(query);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}







	public static void refundTicketFare (int busid) { // get from getbusidlist() return value
		
		String query = "select fare from bus_fare where bus_route = " + busid;
		
		try(	Connection con = DatabaseConnection.getDatabaseConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);){
			
			while(rs.next()) {
		
				System.out.println("Your account XXXXXXXXXX has credited : Rs." + rs.getDouble(1));
				
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}





	public static void getBookNoID(String bookno) {
		
		String query = "select record from booking_records where booking_no = '" + bookno + "'";
		
		try(		Connection con = DatabaseConnection.getDatabaseConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);){
			
			while(rs.next()) {
				
				requestCancelTicket(rs.getInt(1));
				getBusFromID(rs.getInt(1));
				
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void getBusFromID(int booknoid){
		
		String query = "select bus_No from booking_records where record = " + booknoid ;
		
		try(		Connection con = DatabaseConnection.getDatabaseConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);){
		
			
			while(rs.next()) {
				getBusIdFromBus(rs.getInt(1));
			}
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		
		public static void getBusIdFromBus(int busno) {
		
			String query = "select id from bus_list where bus_No = " + busno ;
			
			try(	Connection con = DatabaseConnection.getDatabaseConnection();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query);){
			
				
				if(rs.next()) {
					refundTicketFare (rs.getInt(1));
					
				}
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
	
	

	
	

}

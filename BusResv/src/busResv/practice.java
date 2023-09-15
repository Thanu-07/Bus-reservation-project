package busResv;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import dashboardandfunctionalities.End_UserMenu;
import dashboardandfunctionalities.UserBooking;

public class practice {

	public static void main (String [] args) {

		
		System.out.println( 1 + ") " + "Booking Number : " + "12345678" + " | " + "Passenger Name : " + "Mugesh.A" + " | " + "Age : " + 24 + " | " + "Gender : " + "Male" + " | " + "Bus Number : " + 51 + " | " + "Bus Type : " + "AC:Sleeper" + " | " + "Seat Number : " + 34 + " | " + "Boarding Point : " + "Chennai" + " | " + "Travel Date & Time : " + "2023-01-01 16:00:00" + " | " + "Dropping Point : " + "Nagercovil" + " | " +"Arriving Date & Time : " + "2023-01-02 06:00:00");
		
		
		System.out.println("\n Booking ID : " + "47y87y7293y397" + "    |     + Bus : " + 51 + "    |    Bus Type : " + "Sleeper" + "\n\n-----------------------------------------------------------------------------------------------------\n\n Boarding Point : " + "Chennai" + "          |        Dropping Point : " + "Madurai" + "\n\n Journey Date  : " + "23-09-2023"  + "        |        Dropping Date : " + "24-09-2023"  + "\n Journey Time  : " + "12:00:00" + "          |        Dropping Time : " + "08:00:00" + "\n\n-----------------------------------------------------------------------------------------------------\n\n Passenger Details : " + "Lyju" + " | Age : " + 44 + " | Gender : " + "male" + "\n Seat No           : " + 43);
		System.out.println();
		
		
	
	}
	
	
//	
//	
//	// 1) need to replace this method under userMenuDAO
//	
//	static String SearchBusDAO(String boardpoint,String droppoint,LocalDate traveldate) throws SQLException {
//					
//				
//		String query = "select bus_timing_table.bus_no , bus_list.bus_No, bus_list.Type , bus_list.From_terminal, bus_list.To_Terminal, concat(bus_calender.day_of_month ,'-',bus_calender.months,'-',bus_calender.years) as opreate_date ,bus_timing_table.depart_time , bus_timing_table.arrive_date_id ,bus_timing_table.arrive_time, bus_fare.fare"
//		 + "from bus_timing_table join bus_list on bus_list.id = bus_timing_table.bus_no join bus_Calender on bus_Calender.id = bus_timing_table.operate_date_id join bus_fare on bus_fare.fare_id = bus_list.id"
//		+ " where  bus_list.From_terminal = ? and bus_list.To_Terminal = ? and bus_calender.day_of_month = ? and bus_calender.months = ? and bus_Calender.years = ? ";
//		
//		
//		Connection con = DatabaseConnection.getDatabaseConnection();
//		PreparedStatement pst = con.prepareStatement(query);
//		pst.setString(1, boardpoint);
//		pst.setString(2, droppoint);
//		
//		
//		// convert localdate into sql date using date.value(localdate) converts localdate as string assign to sql date 
//		// java.sql.Date sqldate = Date.valueOf(traveldate);
//		// pst.setDate(3,sqldate);
//		
//		
//		int dayofmonth = traveldate.getDayOfMonth();
//		int month = traveldate.getMonthValue();
//		int year = traveldate.getYear();
//		
//		pst.setInt(3, dayofmonth);
//		pst.setInt(4, month);
//		pst.setInt(5, year);
//		
//		
//		ResultSet rs = pst.executeQuery();
//		
//		while(rs.next()) {
//			return " Bus ID : " + rs.getInt(1) + "|" + " Bus Type : " + rs.getString(3) + "|" + " Bus From : " + rs.getString(4) + " To : " + rs.getString(5) 
//					+ " Operate Date : " + rs.getString(6) + "|" + " Operate Time : " + rs.getTime(7) + "|" + " Arrive Time : " + rs.getTime(9) + "|" + "  Price : ₹" + rs.getInt(10) ;
//		}
//		
//		
//		return "No bus available...";
//		
//		}
//	
//	
//	
//	// 1i)
//	
//	
//	public static ArrayList<Integer>  isBookedSeats(int bus_id, LocalDate date) throws SQLException {
//	
//		
//		ArrayList<Integer> seat = new ArrayList<Integer>();
//		
//		java.sql.Date sqldate = Date.valueOf(date);
//		String strdate = date.toString();
//		
//		String query = "select seat_No from booking_records where bus_No = " +bus_id+ " and  travel_date = " + strdate;
//				
//
//		
//		
//		Connection con = DatabaseConnection.getDatabaseConnection();
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery(query);
//		
//
//		while(rs.next()) {
//			
//				 seat.add(rs.getInt(1));
//				 
//				 
//		}
//		
//		return seat;
//		
//		
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	// 2) need to replace this method under UserBooking
//	
//	
//	 void addBooking(UserBooking ub, End_UserMenu eum, String bookNo, String user ) throws SQLException {
//			// TODO Auto-generated method stub
//			
//			String passengerName = ub.passenger_name;
//			int age = ub.age;
//			String gender	= ub.gender;
//			int busID = eum.getBus_id();
//			int seatNo = ub.getPassSeatNo();
//			LocalDate date = eum.getDate();
//			java.sql.Date sqloperdate = Date.valueOf(date);
//			
//			
//			String arrdate = eum.getArrivedate();
//			SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-yyyy");
//			java.sql.Date sqlarrdate = (Date) sdt.parse(arrdate);
//			
//			
//			
//			int dayofmonth = date.getDayOfMonth();
//			int month = date.getMonthValue();
//			int year = date.getYear();
//			
//			
//			
//			int bus_No =0;
//			String bus_Type="";
//			String boarding_point ="";
//			String dropping_point="";
//			String travel_date ="";
//			String travel_time = "";
//			String arriving_date ="";
//			String arriving_time ="" ;
//			
//			
//			
//			String query =" select bus_list.bus_no , bus_list.Type,  bus_list.From_terminal, bus_list.To_Terminal, concat(bus_calender.day_of_month ,'-',bus_calender.months,'-',bus_calender.years)  as date_of_operate, bus_timing_table.depart_time  , concat(bus_calender.day_of_month ,'-',bus_calender.months,'-',bus_calender.years)  as date_of_arrive,  bus_timing_table.arrive_time"
//			+ " from bus_timing_table join bus_list on bus_list.id = bus_timing_table.bus_no join bus_calender on bus_calender.id = bus_timing_table.operate_date_id"
//			+ " where bus_list.id = ? and bus_calender.day_of_month = ? and bus_calender.months = ? and bus_Calender.years = ?";
//			
//			String addbooking = "insert into Booking_records (booking_no,User_name,passenger_name,age,gender,bus_No,bus_Type,Seat_No,boarding_point,travel_date,travel_time,dropping_point,arriving_date,arriving_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			
//			
//			
//			
//			try (Connection con = DatabaseConnection.getDatabaseConnection();
//					PreparedStatement st = con.prepareStatement(query);
//					PreparedStatement pst = con.prepareStatement(addbooking);){
//				
//				st.setInt(1, busID);
//				st.setInt(2,dayofmonth);
//				st.setInt(3,month);
//				st.setInt(4,year);
//				
//				
//			ResultSet rs = st.executeQuery();
//			
//			while(rs.next()) {
//				bus_No = rs.getInt(1);
//				bus_Type = rs.getString(2);
//				boarding_point = rs.getString(3);
//				travel_date = rs.getString(5);
//				travel_time = rs.getTime(6).toString();
//				dropping_point =  rs.getString(4);
//				arriving_date = rs.getString(7);
//				arriving_time = rs.getTime(8).toString();
//			}
//			
//			
//			
//			
//			
//			
//			
////			java.sql.Date sqldate = Date.valueOf(travelDate);
////			pst.setDate(5, sqldate);
//			
//			pst.setString(1, bookNo);
//			pst.setString(2, user);
//			pst.setString(3,passengerName);
//			pst.setInt(4, age);
//			pst.setString(5, gender);
//			pst.setInt(6, bus_No);
//			
//			pst.setString(7, bus_Type);
//			pst.setInt(8, seatNo);
//			pst.setString(9,boarding_point);
//			pst.setString(10, travel_date);
//			pst.setString(11, travel_time);
//			pst.setString(12, dropping_point);
//			pst.setString(13, arriving_date);
//			pst.setString(14, arriving_time);
//			
//			
//			pst.executeUpdate();
//			
//		}
//	
//	
//	
//	
//	 }
}

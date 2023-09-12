package dashboardandfunctionalities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import Admindashboardfunctionalities.AdminFunction;
import busResv.DatabaseConnection;

public class userMenuDAO {
	
	
//	static  String  SerachBusDA(String boardpoint,String droppoint,LocalDate traveldate) throws SQLException {
//		
//		String from = boardpoint;
//		String to = droppoint;
//		LocalDate date = traveldate;
//		
//		String query = "select bl.id,bl.From_terminal,concat(date(sop.schedule_operating_date_time),' | ',time(sop.schedule_operating_date_time)) as from_date_time ,bl.To_terminal,concat(date(sop.schedule_reaching_date_time),' | ',time(sop.schedule_reaching_date_time)) as to_date_time from bus_list as bl join schedule_of_bus as sop on bl.id = sop.bus_No where bl.From_terminal = ? and bl.To_Terminal = ? and date(sop.schedule_operating_date_time) = ?;";
//	//"select bl.bus_No,bl.From_terminal,bl.To_terminal,sop.schedule_operating_date ,sop.schedule_departtime,sop.schedule_arrivetime from bus_list as bl join schedule_of_bus as sop on bl.id = sop.bus_No where bl.From_terminal = ? and bl.To_Terminal = ? and sop.schedule_operating_date = ?;";
//		
//		java.sql.Date sqldate = Date.valueOf(date);
//		
//		Connection con = DatabaseConnection.getDatabaseConnection();
//		PreparedStatement pst = con.prepareStatement(query);
//		pst.setString(1, from);
//		pst.setString(2, to);
//		
//		
//		pst.setDate(3, sqldate);
//		
//		ResultSet rs = pst.executeQuery();
//		
//		
//			while(rs.next()) {
//			//ViewBusInfo(rs.getInt(1)) ;
//			return "Bus id : " + rs.getInt(1) + "|" + "From Terminal : " + rs.getString(2) + " Date & Time : " + rs.getString(3) + "|" + " To Terminal : " + rs.getString(4) + " Date & Time : " + rs.getString(5); // getTime(4) db 4 th column 
//			
//			}
//	
//		return "No Bus available..";
//	
//	}
	
	static String SearchBusDAO(String boardpoint,String droppoint,LocalDate traveldate) throws SQLException {
		
	
	String query = "select buslist.id , buslist.bus_No, buslist.Type, buslist.From_terminal, buslist.To_Terminal, concat(date(sop.schedule_operating_date_time),' | ',time(sop.schedule_operating_date_time)) as from_date_time,"
			+ " concat(date(sop.schedule_reaching_date_time),' | ',time(sop.schedule_reaching_date_time)) as to_date_time, bus_fare.fare from bus_list as buslist join schedule_of_bus as sop on buslist.id = sop.bus_No join bus_fare on buslist.id = bus_fare.fare_id"
			+ " where buslist.From_terminal = ? and buslist.To_Terminal = ? and date(sop.schedule_operating_date_time) = ? ;";
			
			// note : we have provide some space near  " concat and  " where becz in my sql these lines where written in nextline so it ensuring some space before it.
			// + at the beginning of the next line is often considered more readable and makes it visually clear that you're continuing the string concatenation.
	
	
	
	
	Connection con = DatabaseConnection.getDatabaseConnection();
	PreparedStatement pst = con.prepareStatement(query);
	pst.setString(1, boardpoint);
	pst.setString(2, droppoint);
	java.sql.Date sqldate = Date.valueOf(traveldate);
	pst.setDate(3,sqldate);
	ResultSet rs = pst.executeQuery();
	
	while(rs.next()) {
		return " Bus ID : " + rs.getInt(1) + "|" + " Bus Type : " + rs.getString(3) + "|" + " Bus From : " + rs.getString(4) + " To : " + rs.getString(5) 
				+ " Departing Date & Time : " + rs.getString(6) + "|" + " Arriving Date & Time : " + rs.getString(7) + "|" + "  Price : ₹" + rs.getInt(8) ;
	}
	
	//" Bus Capacity : " + rs.getInt(2) + "|" 
	
	return "No bus available...";
	
	}

	
	int getCapacity(int bus_id) throws SQLException{
		
		String query = "select capacity from bus_list where bus_No = '" + bus_id + "'";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	
	
	public int getBookedCount(int busNo,LocalDate date) throws SQLException{
		
		String query = "select Count(*) from Booking_records where bus_No = ? and travel_date_time = ?";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(query);
		
		java.sql.Date sqldateFormat = Date.valueOf(date);
		pst.setInt(1, busNo);
		pst.setDate(2, sqldateFormat);
		
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		return rs.getInt(1);
		
	}
	
	
	public static boolean isSeatsAvailableDAO(int busno, int seatNo, LocalDate date) throws SQLException {
		// TODO Auto-generated method stub
		
		java.sql.Date sqldate = Date.valueOf(date);
		
		String query = "select count(*) from booking_records where bus_No = '" + busno + "' and seat_No = '" + seatNo + "' and date(travel_date_time) = '"+ date +"'";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			
			if(rs.getInt(1)== 1) 
			return false;
		
		}
		return true;
	}


//	public static ArrayList<Integer> availableSeats(int busid,int seatno, LocalDate date) throws SQLException {
//		
//		ArrayList<Integer> seat = new ArrayList<Integer>();
//		
//		int i =1;
//		String query ;
//		Connection con = DatabaseConnection.getDatabaseConnection();
//		Statement st = con.createStatement();
//		ResultSet rs;
//		
//		switch(busid) {
//		
//		case  51 :
//		
//		 query = "select seat_number from bus_51_seat join bus_list on bus_list.id= bus_51_seat.bus_id join schedule_of_bus on bus_list.id  = schedule_of_bus.schedule_id where bus_list.bus_No and bus_51_seat.seat_number not in ('" + busid + ",'"+seatno+"');";
//	
//		 rs = st.executeQuery(query);
//		 
//		 while(rs.next()) {
//			 
//			 
//			 seat.add( rs.getInt(i));
//			 
//			 i++;
//		 }
//		 break;
//		 
//		
//		
//		case 52 :
//			
//			 query = "select seat_number from bus_51_seat where seat_number not in ('"+ seatno + "')";
//			
//			 rs = st.executeQuery(query);
//			
//			
//		case 53:
//			
//			 query = "select seat_number from bus_51_seat where seat_number not in ('"+ seatno + "')";
//			
//			rs = st.executeQuery(query);
//			
//		case 54 :
//			
//			query = "select seat_number from bus_51_seat where seat_number not in ('"+ seatno + "')";
//			
//			 rs = st.executeQuery(query);
//			
//		}
//		
//		return seat;
//	}


	public static ArrayList<Integer>  isBookedSeats(int bus_id, LocalDate date) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> seat = new ArrayList<Integer>();
		
		java.sql.Date sqldate = Date.valueOf(date);
		
		String query = "select seat_No from booking_records where bus_No = " +bus_id+ " and  date(travel_date_time) in ('" + sqldate + "')"; // in acts as where clause with and operator checks both busNo and date       
		// "select seat_No from booking_records where bus_No and  date(travel_date_time) in ("+ bus_id + ",'" + sqldate + "')"; // in acts as or operator checks/get either busNo or date (so thats why it gets the date matching seats(any other bus)even though bus No is not match)
		
		
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
//		if(!rs.next()) {
//			switch(bus_id) {
//			
//			case 51 :
//				String query2 = "select seat_number from bus_seatInfo where bus_51_seat = " + bus_id;
//				ResultSet rs1 = st.executeQuery(query2);
//				while (rs1.next()) {
//					for(int j=1;j<rs1.getMetaData().getColumnCount();j++) {
//						 seat.add(rs1.getInt(j));
//						 
//						 }
//				}
//				
//				return seat; // empty list
//			
//			case 52 :
//				String query3 = "select seat_number from bus_seatInfo where bus_52_seat = " + bus_id;
//				ResultSet rs2 = st.executeQuery(query3);
//				while(rs2.next()) {
//					for(int j=1;j<rs2.getMetaData().getColumnCount();j++) {
//						 seat.add(rs2.getInt(j));
//						 
//						 }
//				}
//				return seat;
//				
//			case 53 :
//				String query4 = "select seat_number from bus_seatInfo where bus_53_seat = " + bus_id ;
//				ResultSet rs3 = st.executeQuery(query4);
//				while(rs3.next()) {
//					for(int j=1;j<rs3.getMetaData().getColumnCount();j++) {
//						 seat.add(rs3.getInt(j));
//						 
//						 }
//				}
//				return seat;
//				
//			case 54: 
//				
//				String query5 = "select seat_number from bus_seatInfo where bus_54_seat = " + bus_id;
//				ResultSet rs4 = st.executeQuery(query5);
//				while(rs4.next()) {
//					for(int j=1;j<rs4.getMetaData().getColumnCount();j++) {
//						 seat.add(rs4.getInt(j));
//						 
//						 }
//				}
//				return seat;
//				
//		}
//		}
		while(rs.next()) {
			//for(int j=1;j<rs.getMetaData().getColumnCount();j++) {
				 seat.add(rs.getInt(1));
				 
				 //}
		}
		
		return seat;
		
		
		
	}


	public static ArrayList<Integer> availableSeats(int bus_id, ArrayList<Integer> bookedSeats) throws SQLException {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> seat = new ArrayList<Integer>();
		
		int j =1;
		
		String query ;
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs;
		
		
		if(bookedSeats.isEmpty()) {
			
			switch(bus_id) {
			
			case 51 :
				
				 query = "select seat_number from bus_seatInfo where bus_51_seat = " + bus_id;
				 rs = st.executeQuery(query);
				while (rs.next()) {
					//for(j=1;j<rs1.getMetaData().getColumnCount();j++) {
						 seat.add(rs.getInt(1));
						 
						 //}
				}
				return seat; // empty list
			
			case 52 :
			
				 query = "select seat_number from bus_seatInfo where bus_52_seat = " + bus_id;
				 rs = st.executeQuery(query);
				while(rs.next()) {
					//for(j=1;j<rs2.getMetaData().getColumnCount();j++) {
						 seat.add(rs.getInt(1));
						 
						 //}
				}
				return seat;
				
			case 53 :
				
				 query = "select seat_number from bus_seatInfo where bus_53_seat = " + bus_id ;
				 rs = st.executeQuery(query);
				while(rs.next()) {
					//for(j=1;j<rs3.getMetaData().getColumnCount();j++) {
						 seat.add(rs.getInt(1));
						 
						 //}
				}
				return seat;
				
			case 54: 
				
				String query5 = "select seat_number from bus_seatInfo where bus_54_seat = " + bus_id;
				ResultSet rs4 = st.executeQuery(query5);
				while(rs4.next()) {
					//for(j=1;j<rs4.getMetaData().getColumnCount();j++) {
						 seat.add(rs4.getInt(1));
						 
						 //}
				}
				return seat;
			
		}
		
		
		}
		
		
		else {
			
		
		switch(bus_id) {
		
		case  51 :
			
			
	
		 query = "select seat_number from bus_seatInfo where bus_51_seat  = ? and seat_number not in (";
		 for(int i=0;i<bookedSeats.size();i++) {
			 query +=" ?,";
			 
		 }
		 
		 query = query.substring(0, query.length()-1) + ")";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, bus_id);
			
			for(int i=0;i<bookedSeats.size();i++) {
				 pst.setInt(i+2,bookedSeats.get(i));
				 
			 }
			
		  rs = pst.executeQuery();
		 
		 while(rs.next()) {
			 
//			 for(j=1;j<rs.getMetaData().getColumnCount();j++) {
//			 seat.add(rs.getInt(j));
//			 
//			 }
			 
			 seat.add(rs.getInt(1));
		 }
		 
	
	
		 return seat;
		
		
		case 52 :
		
			
				 query = "select seat_number from bus_seatInfo where bus_52_seat = ? and  seat_number not in (";
				for(int i=0;i<bookedSeats.size();i++) {
					query += "?,";
				}
					query = query.substring(0, query.length()-1) + ")";
					PreparedStatement pst1  = con.prepareStatement(query);
					 pst1.setInt(1, bus_id);
					 
					 for(int i=0;i<bookedSeats.size();i++) {
						 pst1.setInt(i+2, bookedSeats.get(i));
					 }
					
				  rs = pst1.executeQuery();
				 
				 while(rs.next()) {
					 
					// for(j=1;j<rs.getMetaData().getColumnCount();j++) {
						 seat.add(rs.getInt(1));
						 
				 //}
				 }
				 
					
			
				 return seat;
			
			
		case 53:
			
	
				 query = "select seat_number from bus_seatInfo where bus_53_seat = ? and seat_number not in (";
				 for(int i=0;i<bookedSeats.size();i++) {
						query += "?,";
					}
						query = query.substring(0, query.length()-1) + ")";
						PreparedStatement pst2  = con.prepareStatement(query);
						 pst2.setInt(1, bus_id);
						 
						 for(int i=0;i<bookedSeats.size();i++) {
							 pst2.setInt(i+2, bookedSeats.get(i));
						 }
					
				  rs = pst2.executeQuery();
				 
				 while(rs.next()) {
					 
					 //for(j=1;j<rs.getMetaData().getColumnCount();j++) {
						 seat.add(rs.getInt(1));
						 
						 //}
				 }
				 
			
			
				 return seat;
			
		case 54 :
	
				 query = "select seat_number from bus_seatInfo where bus_54_seat = ? and seat_number not in (";
				 for(int i=0;i<bookedSeats.size();i++) {
						query += "?,";
					}
						query = query.substring(0, query.length()-1) + ")";
						PreparedStatement pst3  = con.prepareStatement(query);
						 pst3.setInt(1, bus_id);
						 
						 for(int i=0;i<bookedSeats.size();i++) {
							 pst3.setInt(i+2, bookedSeats.get(i));
						 }
					
				  rs = pst3.executeQuery();
				 
				 while(rs.next()) {
					 
					 //for(j=1;j<rs.getMetaData().getColumnCount();j++) {
						 seat.add(rs.getInt(1));
						 
						 //}
				 }
				 
				
			
				 return seat;
		
		}
		}
		return seat;
		
	}
	
	
	
	
	
	
//	public static void availableSeats(int busid, int seatNo) throws SQLException {
//		
//		ArrayList<Integer> seat = new ArrayList<>();
//		
//		int i =1;
//		String query ;
//		Connection con = DatabaseConnection.getDatabaseConnection();
//		Statement st = con.createStatement();
//		ResultSet rs;
//		
//		switch(busid) {
//		
//		case  51 :
//		
//		 query = "select seat_number from bus_51_seats where seat_number not in ('"+ seatNo + "')";
//	
//		 rs = st.executeQuery(query);
//		 
//		 while(rs.next()) {
//			 
//			 
//			 System.out.println(rs.getInt(i));
//			 
//			 i++;
//		 }
//		 
//		 break;
//		
//		
//		case 52 :
//			
//			 query = "select seat_number from bus_51_seat where seat_number not in ('"+ seatNo + "')";
//			
//			 rs = st.executeQuery(query);
//			
//			
//		case 53:
//			
//			 query = "select seat_number from bus_51_seat where seat_number not in ('"+ seatNo + "')";
//			
//			rs = st.executeQuery(query);
//			
//		case 54 :
//			
//			query = "select seat_number from bus_51_seat where seat_number not in ('"+ seatNo + "')";
//			
//			 rs = st.executeQuery(query);
//			
//		}
	


//	public static  ArrayList<Integer> availableSeats(int bus_id, LocalDate date) throws SQLException {
//		
//		ArrayList<Integer> seat = new ArrayList<>();
//		
//		java.sql.Date sqldate= Date.valueOf(date);
//		
//		String query = "select seat_No from booking_records where bus_No and date(travel_date_time) not in ('"+ bus_id +"','" +sqldate+ "')";
//		Connection con = DatabaseConnection.getDatabaseConnection();
//		Statement st = con.createStatement();
//		ResultSet rs = st.executeQuery(query);
//		
//		int i = 1;
//		while(rs.next()) {
//			 
//			seat.add(rs.getInt(i));
//			
//			i++;
//		}
//		
//		
//		return seat;
	
	
	
//	public static ArrayList <String> BookingHistory(String user) {
//		
//		ArrayList <String> bookhis = new ArrayList<>();
//		
//		String query = "select * from booking_records where User_name = ' " + user + "'";
//		
//		try(		Connection con = DatabaseConnection.getDatabaseConnection();
//				Statement st = con.createStatement();
//				ResultSet rs = st.executeQuery(query);){
//			
//			int i = 1;
//			while(rs.next()) {
//			 
//				bookhis.add(i++ +") " + "Booking Number : " + rs.getString(2) + " | " + "Passenger Name : " + rs.getString(4) + " | " + "Age : " + rs.getInt(5) + " | " + "Gender : " + rs.getString(6) + " | " + "Bus Number : " + rs.getInt(7) + " | " + "Bus Type : " + rs.getString(8) + " | " + "Seat Number : " + rs.getInt(9) + " | " + "Boarding Point : " + rs.getString(10) + " | " + "Travel Date & Time : " + rs.getString(11) + " | " + "Dropping Point : " + rs.getString(12) + " | " +"Arriving Date & Time : " + rs.getString(13));
//			
//		}
//			
//		}
//		
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return bookhis;
//		
//	}
//	
//	
//	public static String BookingCancellation(String bookno){
//		
//		AdminFunction af = new AdminFunction();
//	
//		
//		String query = "select booking_no from booking_records where booking_no = '" +bookno +"'";
//		
//		
//		try(	Connection con = DatabaseConnection.getDatabaseConnection();
//				Statement st = con.createStatement();
//				ResultSet rs = st.executeQuery(query);){
//			
//			int i = 1;
//			while(rs.next()) {
//				
//				AdminFunction.getBookNoID(rs.getString(1));
//				
//					return true;
//				
//		
//				
//			}
//		
//		
//	}
//		catch(Exception e ) {
//			e.printStackTrace();
//		}
//		
//		return false;
//	
//	}
	
	
	


}

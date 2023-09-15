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
	
	

// orginal :	
//	static String SearchBusDAO(String boardpoint,String droppoint,LocalDate traveldate) throws SQLException {
//		
//	
//	String query = "select buslist.id , buslist.bus_No, buslist.Type, buslist.From_terminal, buslist.To_Terminal, concat(date(sop.schedule_operating_date_time),' | ',time(sop.schedule_operating_date_time)) as from_date_time,"
//			+ " concat(date(sop.schedule_reaching_date_time),' | ',time(sop.schedule_reaching_date_time)) as to_date_time, bus_fare.fare from bus_list as buslist join schedule_of_bus as sop on buslist.id = sop.bus_No join bus_fare on buslist.id = bus_fare.fare_id"
//			+ " where buslist.From_terminal = ? and buslist.To_Terminal = ? and date(sop.schedule_operating_date_time) = ? ;";
//			
//			// note : we have provide some space near  " concat and  " where becz in my sql these lines where written in nextline so it ensuring some space before it.
//			// + at the beginning of the next line is often considered more readable and makes it visually clear that you're continuing the string concatenation.
//	
//	
//	
//	
//	Connection con = DatabaseConnection.getDatabaseConnection();
//	PreparedStatement pst = con.prepareStatement(query);
//	pst.setString(1, boardpoint);
//	pst.setString(2, droppoint);
//	java.sql.Date sqldate = Date.valueOf(traveldate);
//	pst.setDate(3,sqldate);
//	ResultSet rs = pst.executeQuery();
//	
//	while(rs.next()) {
//		return " Bus ID : " + rs.getInt(1) + "|" + " Bus Type : " + rs.getString(3) + "|" + " Bus From : " + rs.getString(4) + " To : " + rs.getString(5) 
//				+ " Departing Date & Time : " + rs.getString(6) + "|" + " Arriving Date & Time : " + rs.getString(7) + "|" + "  Price : ₹" + rs.getInt(8) ;
//	}
//	
//	//" Bus Capacity : " + rs.getInt(2) + "|" 
//	
//	return "No bus available...";
//	
//	}
	
	
	static String SearchBusDAO(String boardpoint,String droppoint,LocalDate traveldate) throws SQLException {
		
		int dayofmonth = traveldate.getDayOfMonth();
		int month = traveldate.getMonthValue();
		int year = traveldate.getYear();
		
		
		String query = "select bus_timing_table.bus_no , bus_list.bus_No, bus_list.Type , bus_list.From_terminal, bus_list.To_Terminal, concat(bus_calender.day_of_month ,'-',bus_calender.months,'-',bus_calender.years) as operate_date ,"
				+ " bus_timing_table.depart_time , bus_timing_table.arrive_date_id ,bus_timing_table.arrive_time, bus_fare.fare\r\n"
				+ "	from bus_timing_table join bus_list on bus_list.id = bus_timing_table.bus_no join bus_Calender on bus_Calender.id = bus_timing_table.operate_date_id join bus_fare on bus_fare.fare_id = bus_list.id"
				+ "	where  bus_list.From_terminal = ? and bus_list.To_Terminal = ? and bus_calender.day_of_month = ? and bus_calender.months = ? and bus_Calender.years = ?";
		
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, boardpoint);
		pst.setString(2, droppoint);
		
		
		// convert localdate into sql date using date.value(localdate) converts localdate as string assign to sql date 
		// java.sql.Date sqldate = Date.valueOf(traveldate);
		// pst.setDate(3,sqldate);
		
		
		pst.setInt(3, dayofmonth);
		pst.setInt(4, month);
		pst.setInt(5, year);
		
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			return " Bus ID : " + rs.getInt(1) + "|" + " Bus Type : " + rs.getString(3) + "|" + " Bus From : " + rs.getString(4) + " To : " + rs.getString(5) + "|"
					+ " Operate Date : " + rs.getString(6) + "|" + " Operate Time : " + rs.getTime(7) + "|" + " Arrive Time : " + rs.getTime(9) + "|" + "  Price : ₹" + rs.getInt(10) ;
		}
		
		
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
		
		
		 String query = "select Count(*) from Booking_records where bus_No = ? and travel_date = ?";
		
		// orginal :
		//String query = "select Count(*) from Booking_records where bus_No = ? and travel_date_time = ?";
		
		try(Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(query);){
		
		java.sql.Date sqldateformat = Date.valueOf(date);
		pst.setInt(1, busNo);
		//pst.setDate(2, sqldateFormat);
		pst.setDate(2, sqldateformat);
		
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		
		return rs.getInt(1);
		}
		
	}
	
	
	public static boolean isSeatsAvailableDAO(int busno, int seatNo, LocalDate date) throws SQLException {
		
		
		java.sql.Date sqldateformat = Date.valueOf(date);
		
		
		
		String query = "select count(*) from booking_records where bus_No = '" + busno + "' and seat_No = '" + seatNo + "' and travel_date = "+ sqldateformat;
		
		// orginal :
		//String query = "select count(*) from booking_records where bus_No = '" + busno + "' and seat_No = '" + seatNo + "' and date(travel_date_time) = '"+ date +"'";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			
			if(rs.getInt(1)== 1) 
			return false;
		
		}
		return true;
	}




	public static ArrayList<Integer>  isBookedSeats(int bus_id, LocalDate date) throws SQLException {
		
		
		ArrayList<Integer> seat = new ArrayList<Integer>();
		
		java.sql.Date sqldate = Date.valueOf(date);
		
		
		
		
		// orginal
		//String query = "select seat_No from booking_records where bus_No = " +bus_id+ " and  date(travel_date_time) in ('" + sqldate + "')"; // in acts as where clause with and operator checks both busNo and date       
		// "select seat_No from booking_records where bus_No and  date(travel_date_time) in ("+ bus_id + ",'" + sqldate + "')"; // in acts as or operator checks/get either busNo or date (so thats why it gets the date matching seats(any other bus)even though bus No is not match)
		
		// need to modify new query :
		
		String query = "select seat_No from booking_records where bus_No = " +bus_id+ " and  travel_date =" + sqldate;
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		

		while(rs.next()) {
			
				 seat.add(rs.getInt(1));
				 
				 
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


	public static String getArriveDate(String struserbus, LocalDate date) {
		
		try {
		
		int busid = Integer.parseInt(struserbus);
		
		
		
		int dayofmonth = date.getDayOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		
		
		
		
		int operatedateid = getOperateDateid(busid,dayofmonth,month,year);
		int arrivedateid = getArriveDateid(busid,operatedateid);
		
		
		String query = " select concat(bus_calender.day_of_month ,'-',bus_calender.months,'-',bus_calender.years)  as date_of_arrival from bus_timing_table "
				+ "join bus_list on bus_list.id = bus_timing_table.bus_no join bus_calender on bus_calender.id = bus_timing_table.arrive_date_id"
				+ " where bus_list.id = ? and bus_timing_table.arrive_date_id = ?";
		
	
		
		try(Connection con = DatabaseConnection.getDatabaseConnection();
				PreparedStatement st = con.prepareStatement(query);){
				
				st.setInt(1, busid);
				st.setInt(2, arrivedateid);
				
			
				ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String arrivedate  = rs.getString(1);
				return arrivedate;
			}
		
		
		
		}
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return "";
		
		
	}




	private static int getOperateDateid(int busid, int dayofmonth, int month, int year) {
		
		String query = "select bus_timing_table.operate_date_id  from bus_timing_table join bus_list on bus_list.id = bus_timing_table.bus_no join bus_calender on bus_calender.id = bus_timing_table.operate_date_id"
				+ " where bus_list.id = ? and bus_calender.day_of_month = ? and bus_calender.months = ? and bus_Calender.years = ?";
		
		int id = 0;
		
		try(Connection con = DatabaseConnection.getDatabaseConnection();
				PreparedStatement st = con.prepareStatement(query);){
				
				st.setInt(1, busid);
				st.setInt(2, dayofmonth);
				st.setInt(3, month);
				st.setInt(4, year);
			
				ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				id  = rs.getInt(1);
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return id;
	}
	
	
	private static int getArriveDateid(int busid, int operatedateid) {
		
		
		String query = " select bus_timing_table.arrive_date_id  from bus_timing_table join bus_list on bus_list.id = bus_timing_table.bus_no join bus_calender on bus_calender.id = bus_timing_table.operate_date_id"
				+ " where bus_list.id = ? and bus_timing_table.operate_date_id = ?";
		
		int id = 0;
		
		try(Connection con = DatabaseConnection.getDatabaseConnection();
				PreparedStatement st = con.prepareStatement(query);){
				
				st.setInt(1, busid);
				st.setInt(2, operatedateid);
				
			
				ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				id  = rs.getInt(1);
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return id;
	}
	
	
	

	


}

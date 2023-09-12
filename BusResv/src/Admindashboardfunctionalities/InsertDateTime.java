package Admindashboardfunctionalities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
//import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

import busResv.DatabaseConnection;

public class InsertDateTime {
	
	
	private int day;
	private int month;
	private int currentYear;
	
	

	private static String dayname;

	public static void main(String[] args) {
	
		InsertDateTime dt = new InsertDateTime();
		dt.Insert_Date_Time();
	
	}
	 
	private  void Insert_Date_Time() {

		LocalDate currentDate = LocalDate.now();

		LocalDate endDate = LocalDate.of(2023, 12, 31);

		while (!currentDate.isAfter(endDate)) { // (currentDate.getYear() == currentYear)

			 day = currentDate.getDayOfMonth();

			month = currentDate.getMonthValue();

			//Month monthname = currentDate.getMonth();

			 currentYear = currentDate.getYear();

			DayOfWeek dayofweek = currentDate.getDayOfWeek();

			int id;

			switch (dayofweek) {

			case MONDAY:

				dayname = dayofweek.name();

				InsertDate(day, month, currentYear, dayname);

				id = getIDofWeek(day, month, currentYear);

				InsertTime(id, getAllBuses());

				break;

			case TUESDAY:

				dayname = dayofweek.name();

				InsertDate(day, month, currentYear, dayname);

				id = getIDofWeek(day, month, currentYear);

				InsertTime(id, getAllBuses());

				break;

			case WEDNESDAY:

				dayname = dayofweek.name();

				InsertDate(day, month, currentYear, dayname);

				id = getIDofWeek(day, month, currentYear);

				InsertTime(id, getAllBuses());

				break;

			case THURSDAY:

				dayname = dayofweek.name();

				InsertDate(day, month, currentYear, dayname);

				id = getIDofWeek(day, month, currentYear);

				InsertTime(id, getAllBuses());

				break;

			case FRIDAY:

				dayname = dayofweek.name();

				InsertDate(day, month, currentYear, dayname);

				id = getIDofWeek(day, month, currentYear);

				InsertTime(id, getAllBuses());

				break;

			case SATURDAY:

				dayname = dayofweek.name();

				InsertDate(day, month, currentYear, dayname);

				id = getIDofWeek(day, month, currentYear);

				InsertTime(id, getAllBuses());

				break;

			case SUNDAY:

				dayname = dayofweek.name();

				InsertDate(day, month, currentYear, dayname);

				id = getIDofWeek(day, month, currentYear);

				InsertTime(id, getAllBuses());

				break;

			}

			currentDate = currentDate.plusDays(1);

		}

	}

	



	private static int getIDofWeek(int day, int month, int currentYear) {
		
		int dateid = 0;
		
		String query = "select id from Bus_Calender where Day_of_Month = ? and Months = ? and Years = ?";
		
		try {
			Connection con = DatabaseConnection.getDatabaseConnection();
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, day);
			pst.setInt(2, month);
			
			pst.setInt(3, currentYear);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				dateid = rs.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return dateid;
		
		
	}



	private static int InsertDate(int day, int month, int currentYear, String dayname){
		
		int count = 0;
		
		String query = "insert into Bus_Calender (Day_of_Week,Day_of_Month,Months,Years) values (?,?,?,?)";
		
		try {
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setString(1, dayname);
		pst.setInt(2, day);
		pst.setInt(3, month);
		pst.setInt(4, currentYear);
		
		int rows = pst.executeUpdate();
		
		count+=rows;
		
		System.out.println("Rows affected : " + rows);
		
		pst.close();
        con.close();
		
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return count;
		
		
	}	
	
			

	
	
	static ArrayList<Integer> getAllBuses(){
		
	ArrayList<Integer> ar = new ArrayList<>();
		
		String query = "select id from bus_list";
		
		try {
			Connection con = DatabaseConnection.getDatabaseConnection();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				//int id = rs.getInt(1);
				
				//ar.add(Arrays.asList(id,"18:30:00","07:05:00"));
				
				ar.add(rs.getInt(1));
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return ar;
		
		
	}
	
	
	private static void InsertTime(int id, ArrayList<Integer> allBuses) {
		
		String query = "insert into Bus_Timing_Table (date_id,depart_time,arrive_time,bus_no) values (?,?,?,?)";
		
		try (
			
			Connection con = DatabaseConnection.getDatabaseConnection();
			
			PreparedStatement pst = con.prepareStatement(query);){ //  try-with-resources  ava ensures that these resources are automatically closed when you exit the try block
			
			for(Integer i : allBuses) {
				
				
				switch(i) {
				
				case 1:

					pst.setInt(1, id);
					pst.setTime(2, java.sql.Time.valueOf("18:30:00"));
					pst.setTime(3, java.sql.Time.valueOf("07:05:00"));
					pst.setInt(4, i);

					break;
			
				case 2 : 
					
					
					pst.setInt(1, id);
					pst.setTime(2, java.sql.Time.valueOf("18:30:00"));
					pst.setTime(3, java.sql.Time.valueOf("07:00:00"));
					pst.setInt(4, i);
					
					break;
					
					
				case 3 :
					
					pst.setInt(1, id);
					pst.setTime(2, java.sql.Time.valueOf("21:30:00"));
					pst.setTime(3, java.sql.Time.valueOf("04:45:00"));
					pst.setInt(4, i);
					
					break;
					
					
					
				case 4 :
					
					pst.setInt(1, id);
					pst.setTime(2, java.sql.Time.valueOf("21:30:00"));
					pst.setTime(3, java.sql.Time.valueOf("04:45:00"));
					pst.setInt(4, i);
					
					break;
			
				}
			
			 pst.executeUpdate();
			
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	
	}
	
	
	 String updateTimeSchedule(Scanner s,ArrayList<Integer> a){
		
		 System.out.println("Enter date as mentioned : dd-MM-YYYY");
		 
		 String strdate = s.nextLine();
		
		 try {
		 
		 LocalDate date = LocalDate.parse(strdate);
		 
		day = date.getDayOfMonth();
		month = date.getMonthValue();
		currentYear = date.getYear();
		
		int id = getIDofWeek(day,month,currentYear);
		
		if(id == 0) {
			return "No time schedule found...";
		}
		
		String query = "update Bus_Timing_Table set depart_time = ? ,arrive_time = ? where date_id = ? and bus_no = ?";
		
		try (Connection con = DatabaseConnection.getDatabaseConnection();
				
				PreparedStatement pst = con.prepareStatement(query);){
			
			System.out.println("Enter departime format as : \"hh:mm:ss\"");
			
			String deptime = s.nextLine();
			
			LocalTime lc = LocalTime.parse(deptime);
			
			
			java.sql.Time sqldeptime = java.sql.Time.valueOf(lc);
			
			pst.setTime(1, sqldeptime);
			
			
			
			
			System.out.println("Enter arrivetime format as : \"hh:mm:ss\"");
			
			String arrivetime = s.nextLine();
			
			
			java.sql.Time sqlarrtime = java.sql.Time.valueOf(LocalTime.parse(arrivetime));
			
			pst.setTime(2, sqlarrtime);
			
			
			pst.setInt(3, id);
			
			System.out.println("Available Buses : ");
			
			for(Integer i : a) {
				System.out.println(i);
			}
			
			System.out.println("\n Enter Bus Number : ");
			
			int bus_no = s.nextInt();
			
			if(a.contains(bus_no)) {
			
			pst.setInt(4, bus_no);
			
			}
			
			else
				
				System.out.println("Invalid Bus.....");
			
			
			
			pst.executeUpdate();
		}
		
		
		
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		 return "";
	
	 }
	
	 
	 static ArrayList<Integer> Allbuses() throws SQLException{
		 
		 ArrayList<Integer> ar = new ArrayList<>();
		 
		 String query = "select id from bus_list ";
		 
		 try(Connection con = DatabaseConnection.getDatabaseConnection();
				
				Statement pst = con.createStatement();
			 
			 ResultSet rs = pst.executeQuery(query);){
			 
			 
			 while(rs.next()) {
				 ar.add(rs.getInt(1));
			 }
		 
		}
		 
		 return ar;
	 }
	

}

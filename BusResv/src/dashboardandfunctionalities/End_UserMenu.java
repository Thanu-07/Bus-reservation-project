package dashboardandfunctionalities;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Admindashboardfunctionalities.AdminFunction;
import busResv.DatabaseConnection;






public class End_UserMenu {
	
	
	private String from; // boardingpoint
	private String to; // dropping point
	private LocalDate date;
	private  int bus_id;
	private int busSeat;
	
	public int getBus_id() {
		return bus_id;
	}
	
	public int getBusSeat() {
		return busSeat;
	}
	
	public void setBusSeat(int busSeat) {
		this.busSeat = busSeat;
	}
	
	public String getFrom() {
		return from;
	}


	public String getTo() {
		return to;
	}


	public LocalDate getDate() {
		return date;
	}


	String pattern = "^[A-Za-z\\s]*$"; // ^[a-zA-Z]*$";
	
	public String SearchBus(Scanner input,String from,String to,LocalDate date) {
		
		
		int option = 1;
		
		String struserbus = "";
		//ArrayList<String> str = new ArrayList<>();
		userMenuDAO usermenu = new userMenuDAO();
		
		try {
//			System.out.println(userMenuDAO.SearchBusDAO(from,to,date));
			
			System.out.println("\nBuses Available : \n");
			
			ArrayList<String> str = new ArrayList<>();
			str.add(userMenuDAO.SearchBusDAO(from,to,date));
			for(int i =0;i<str.size();i++) {
				if(str.get(i).equals("No bus available...")) {
					return "No bus available...";
				}
				System.out.println((i + 1) + ". " + str.get(i));
			}
			
			
			
			while(option==1) {
			
				try {
					System.out.println("\nEnter the option : ");
					option = input.nextInt();
				}
				catch(InputMismatchException e) {
					System.out.println("Invalid Option...");
					input.nextLine();
					option = 1;
				}
			
			if(option >= 1 && option <= str.size()) {
				 struserbus = str.get(option-1);
				
				int colonindex = struserbus.indexOf(":");
				int pipelinecharindex = struserbus.indexOf("|");
				struserbus = struserbus.substring(colonindex + 1, pipelinecharindex).trim();// trim() -> Returns a string whose value is this string, with all leadingand trailing space removed
				// inclusive & exclusive  (colonIndex + 1) and includes characters up to, but not including, the pipe character (pipeIndex).
				option = 2;
				
			
			}

		}

	} catch (Exception e) {
		System.out.println(e);
		input.nextLine();
	}
	return struserbus;
}
	


//	public static void main(String[] args) {
//		
//		boolean present = false;
//		int usrOpt = 1;
//		Scanner s = new Scanner(System.in);
//		
//		End_UserMenu eum = new End_UserMenu();
//		
////		System.out.println("Enter 1 ) Modify Boarding Point \n 2) Modify Dropping Point \n 3) Modify Date");
//		
//	
//				eum.CheckValidBoardPoint(s);
//
//				eum.CheckValidDropPoint(s);
//
//				eum.CheckValidTravelDate(s);
//				
//				
//				//eum.getBus(eum.SearchBus(s,eum.from,eum.to,eum.date));
//
//				String temp = eum.SearchBus(s,eum.from,eum.to,eum.date);
//				
//				System.out.println(eum.getBusId(temp));
//				
//				
//				try {
//					
//					if(eum.isAvailable()) {
//						
//						userMenuDAO ud = new userMenuDAO();
//
////						ArrayList<Integer> seatAvailable = viewSeats(ud.getCapacity(eum.bus_id)); //userMenuDAO.availableSeats(eum.bus_id,eum.date);
//						//availableSeats(seatAvailable);
//
//						
//						
////						for(Integer i : seatAvailable) {
////							System.out.println(i);
////						}
//	
//						 
//						eum.busSeat = eum.selectSeat(s,ud.getCapacity(eum.bus_id));
//						UserBooking ub = new UserBooking();
//						System.out.println("Price Details : " + UserBooking.PriceDetails(eum.bus_id));
//						ub.PaymentOption(s,UserBooking.PriceDetails(eum.bus_id));
//						
//						
//						GenerateBookNo gb = new GenerateBookNo();
//						String bookNo = GenerateBookNo.generateBookNo(gb.getLength());
//						
//						while (GenerateBookNo.CheckBookNo(bookNo)) {
//							bookNo = GenerateBookNo.generateBookNo(gb.getLength());
//						}
//						
//						
//						
//						
//						
//						
//						
//						ub.addBooking(ub,eum,bookNo);
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//		
//			
//				
//				
//				
//				
////				System.out.println("Enter option : ");
////		int opt = s.nextInt();
//		
////		 opt == userMenuDAO.ViewBusInfo(opt) ;
//		
//		
////		System.out.println(eum.date); // 2023-12-12
////		
//		
//		//getBusSchedule(eum.CheckValidTravelDate(s));
//		
//		
//	}
//	

	public HashSet<Integer> selectSeat(Scanner s,int busCapacity) throws SQLException {
		
		HashSet<Integer> seatnos = new HashSet<>();
			
		ArrayList<Integer> availableSeats = userMenuDAO.availableSeats(bus_id,userMenuDAO.isBookedSeats(bus_id,date));
		
		System.out.println("Available Seats are : ");
		
		for(Integer i : availableSeats) {
			System.out.println(i);
		}
		
		
		
		
	//	boolean found = false;
		
			
			try {
			
			System.out.println("Enter total no of seats :");
			int totalSeat = s.nextInt();
		
			
			int i=1;
			
			
			
			while(i<=totalSeat) {
				
				System.out.println("Select Seat:");
				
				int seatNo = s.nextInt();
				
				if(!seatnos.contains(seatNo)) {
				
				
			
			String checkSeat = selectSeat(seatNo,availableSeats);
			
			if(checkSeat.equals("")) {
			
				i++;
				
				seatnos.add(seatNo);
				
				//return seatNo ;
				
				}
			else if(checkSeat.equals("Seat Already Booked")) {
				System.out.println("Seat Already Booked...Select Another Seat");
			}
			else
				System.out.println("Incorrect seat number, provide a correct seat number...!");
			}	
			
				else
				System.out.println("Seat already selected...");
		
			}	
				
			} catch(Exception e) {
				System.out.println("Invalid Number....");
				s.nextLine();
			}
		
		return seatnos;
	}	
//		
//		while(!found) {
//		
//			try {
//				
//				
//				
//				System.out.println("Select Seat:");
//		
//				for(int i=1;i<=totalSeat;i++) {
//				
//			     int seatNo = s.nextInt();
//				
//		if(availableSeats.contains(seatNo)) { // seatNo <= seatAvailable.size() && 
//			
//			// checking seat available for the bus from db
//			
//			if(userMenuDAO.isSeatsAvailableDAO(bus_id,seatNo,date)) { // true
//				
//				//if(userMenuDAO.isBookedSeats(bus_id,date).isEmpty())
//				
//				found = true;
//				
//				return seatNo;
//	
//				
//				//userMenuDAO.availableSeats(bus_id,seatNo,date);
//				
//				
//				//availableSeats(seatAvailable);
//				
//			}
//			else
//				System.out.println("Seat Already Booked...Select Another Seat");
//				
//				
//		}
//		else {
//			System.out.println("Incorrect seat number provide a correct seat number...!");
//			
//		}
//		
//				}
//		
//		}
//		catch(Exception e) {
//			System.out.println(e);
//			s.nextLine();
//			System.out.println("Select Seat:");
//		}
//		
//		}
//
//		return 0;
//	}



private String selectSeat(int seatNo,ArrayList<Integer> availableSeats) throws SQLException {
	
	
//	System.out.println("Available Seats are : ");
//	
//	for(Integer i : availableSeats) {
//		System.out.println(i);
//	}
	
	
	if(availableSeats.contains(seatNo)) { 
		
		if(userMenuDAO.isSeatsAvailableDAO(bus_id,seatNo,date)) { 
		
			
			return "";

			
		}
		else
			
			return "Seat Already Booked";
			
	}
	else {
		
		return "Incorrect seat number";}
		
	
	
}









//	private static ArrayList<Integer> availableSeats(ArrayList<Integer> seatAvailable) {
//		// TODO Auto-generated method stub
//		
//		ArrayList<Integer> seat = new ArrayList<>();
//		
//		System.out.println("Seats Available : ");
//		
//		for(int i=0;i<seatAvailable.size();i++) {
//		
//		//System.out.println(seatAvailable.get(i));
//			seat.add(seatAvailable.get(i));
//		}
//		return seat;
//	}



	private static ArrayList<Integer> viewSeats(int capacity) {
		// TODO Auto-generated method stub
		
		int seatNo ;
		
		ArrayList<Integer> seat = new ArrayList<>();
		
		for(seatNo= 0;seatNo<capacity;seatNo++) {
			seat.add(seatNo);
		}
		
		return seat;
		
	}



	public  String CheckValidBoardPoint(Scanner s) {
		
		boolean a = false;
		
		System.out.println("Enter Boarding Point : ");
		
		while(!a) {
		from = s.nextLine();
		
		if(from.matches(pattern)) {  // !from.equals(" ") &&
			
			if(!from.matches("^\s*$")) {
			a= true;
			return from;
			}
			else
			{System.out.println("Incorrect data.......");
			System.out.println("Enter Boarding Point : ");}
		}
		else
			{System.out.println("Incorrect data.......");
			System.out.println("Enter Boarding Point : ");}
		}
		
		return "";
	}
	
	
	public String CheckValidDropPoint(Scanner s) {
		
		 boolean b = false;
		
		System.out.println("Enter Dropping Point : ");
		
		while (!b) {
		
		to = s.nextLine();
		
		if(to.matches(pattern)) {  // !from.equals(" ") &&
			
			if(!to.matches("^\s*$")) {
			b = true;
			return to;
			}
			else
			{System.out.println("Incorrect data.......");
			System.out.println("Enter Dropping Point : ");}
		}
		else
			{System.out.println("Incorrect data.......");
			System.out.println("Enter Dropping Point : ");}
		}
		
		return "";
	}
	
	
	public LocalDate CheckValidTravelDate(Scanner s) {
		
		
		boolean found = false;
		String strdate ;
		String datepattern = "^(0[1-9]|1\\d|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-\\d{4}$";
		
		System.out.println("Enter Travel Date : as dd-MM-yyyy");
		
		
		while(!found) {
			
			strdate = s.next();
		
		if(!strdate.equals("") && strdate.matches(datepattern)) {
			
			DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // used to convert string text to date
		

			date = LocalDate.parse(strdate,dateformat);
			found = true;
		
		
		}
		
		else
			{System.out.println("Invalid date format , Use the date format mentioned in rules.");
			System.out.println("Enter Travel Date : ");}
		}
		
		return date;
	}
		
	
	
	
	
	private static void getBusSchedule(LocalDate date2) {
		// TODO Auto-generated method stub
		
		if (date2.equals(LocalDate.now()) || date2.isAfter(LocalDate.now())) {
			
			if(date2.equals(LocalDate.now())) {
				LocalTime lt = LocalTime.now(); 
				
				// select * from bus schedule where date = date2 and time > lt (current time);
			}
			
			else if (date2.isAfter(LocalDate.now())) {
				
				int day = date2.getDayOfMonth(); // the day-of-month, from 1 to 31
				int month = date2.getMonthValue();
				int year = date2.getYear();
				
				
				
				// select * from bus schedule where bus_route = che-mdi , schedule_day = day;
			}
			else
				System.out.println("Oops Something went wrong.....");
			
		}
		


		
		
	}
	
	
	private static void ViewSeats(int userbusno) {
		// TODO Auto-generated method stub
		
	}
	
	
	
//private  int getBus(int i) {
//		
//		
//		int option = 1;
//		int userbusno = 0;
//		String struserbus = "";
//		//ArrayList<String> str = new ArrayList<>();
//		userMenuDAO usermenu = new userMenuDAO();
//		
//		try {
////			System.out.println(userMenuDAO.SearchBusDAO(from,to,date));
//			
//			ArrayList<String> str = new ArrayList<>();
//			str.add(userMenuDAO.SearchBusDAO(from,to,date));
//			for(int i1 =0;i1<str.size();i1++) {
//				if(str.get(i1).equals("No bus available...")) {
//					break;
//				}
//				System.out.println((i1 + 1) + ". " + str.get(i1));
//			}
//			
//			System.out.println("Enter the option : ");
//			
//			while(option==1) {
//			
//				try {
//					option = input.nextInt();
//				}
//				catch(InputMismatchException e) {
//					System.out.println("Invalid Option...");
//					input.nextLine();
//					option = 1;
//				}
//			
//			if(option >= 1 && option <= str.size()) {
//				 struserbus = str.get(option-1);
//				
//				int colonindex = struserbus.indexOf(":");
//				int pipelinecharindex = struserbus.indexOf("|");
//				String sub = struserbus.substring(colonindex + 1, pipelinecharindex).trim();// trim() -> Returns a string whose value is this string, with all leadingand trailing space removed
//				// inclusive & exclusive  (colonIndex + 1) and includes characters up to, but not including, the pipe character (pipeIndex).
//				
//				try {
//					
//				 bus_id = Integer.parseInt(sub);
//
//				 System.out.println("Bus No : " + userbusno);
//				 //End_UserMenu.ViewSeats(userbusno);
//				 
//				 option =2;
//				 return bus_id;
//				
//				}
//				catch(NumberFormatException nfe) {
//					System.out.println(nfe.getMessage());
//				}
//				
//				catch(Exception e) {
//					System.out.println(e.getMessage());
//				}
//				
//				//userMenuDAO.ViewSeatsDAO(userbusno);
//				// End_UserMenu.displaySeatLayout();
//			}
//			else
//				{System.out.println("Invalid Option...");
//				System.out.println("Enter the option : ");
//				option = 1;}
//			}
//			
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return bus_id;
//	}

	

	public int getBusId(String temp) {
		int bus_No;
		
		
		
		try {
			
			if(!temp.equals("No bus available...")) {
				bus_No = Integer.parseInt(temp);
				  bus_id = UserBooking.getBusNoDAO(bus_No);
			} 
			else
				System.out.println("No bus available....");
			
		} 
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return bus_id;
	}

	

	
	public boolean isAvailable() throws SQLException {
		
		userMenuDAO umd = new userMenuDAO();
		
		int capacity = umd.getCapacity(this.bus_id);
		
		int bookedCount = umd.getBookedCount(this.bus_id, this.date);
		
		return  bookedCount < capacity  ? true:false;
		
	}
	
	


	
	
}

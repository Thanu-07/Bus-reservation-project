package dashboardandfunctionalities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import busResv.DatabaseConnection;
import registeranglogin.RegistLogIn;

public class UserBooking {
	
	String passenger_name;
	int age;
	String gender ;
	String email;
	String mobile_no;
	
	private int PassSeatNo;
	
	
	
	public int getPassSeatNo() {
		return PassSeatNo;
	}





	public void setPassSeatNo(int passSeatNo) {
		PassSeatNo = passSeatNo;
	}





	UserBooking(){
//		Scanner scanner = new Scanner(System.in);
//		CheckingValidName(scanner);
//		CheckingValidAge(scanner);
//		
//		
//		CheckValidGender(scanner);
	
		
		
		
		
//		CheckValidEmail(scanner);
//		CheckValidMobileNumber(scanner);
		
		
		
		
	}
	
	
	


	public String CheckingValidName(Scanner s) {
		
		
		System.out.println("Enter name of passenger : ");
		passenger_name = s.nextLine();
		
		while(!passenger_name.matches(("^[a-zA-Z.]*$"))) { // runs loop upto passName not matches pattern
			
			System.out.println("------------------------------------------");
			
			System.out.println("Invalid Name....! \nName must not has : \nNumbers, \nSpecial_Characters, \nSpace");
			
			System.out.println("-----------------------------------------");
			
			System.out.println("Enter the correct name : ");
			passenger_name = s.nextLine();
			
			}
	
	
		    
			return passenger_name;
			
		
	}
	
	public int CheckingValidAge(Scanner scanner) {
		
		int travel = 1;
		
		System.out.println("Enter age : ");
		
		
		while(travel == 1) {
		
			try {
		
				age = scanner.nextInt();
		
				if(age > 0 && age < 80) {
			
					travel = 2;
			
		
				}
	
				else if (age > 80 && age < 100) {
			
					System.out.println("Passenger above age : 80  can't allowed to travel in Bus as per rules...");
			
					System.out.println("Enter age : ");
					
		
				}
		
				else
			
				{System.out.println("Invalid Age...");
		    
				System.out.println("Enter age : ");}
			
		
			}
		
			catch(Exception e) {
			//System.out.println(e.getMessage());
			
				scanner.nextLine();
			
				System.out.println("Not a valid Age...");
			
				System.out.println("Enter age : ");
		
			}
	 
		}
		return age;
	}
	
	
	public String CheckValidGender(Scanner scanner) {
		
		//scanner.nextLine();
		
		boolean found = false;
		
		
		
		while(!found) {
			
			System.out.println("Select Gender : Male | Female ");
			
			try {
				gender = scanner.nextLine();
				if (gender.equals("Male") || gender.equals("Female") || gender.equals("male") || gender.equals("female")) {
					found = true;
					return gender;
					
				} else
					System.out.println("Incorrect Option....");
					
					

			} catch (Exception e) {
				//System.out.println(e.getMessage());
				scanner.nextLine();
				System.out.println("Incorrect Option....");
		

			}
		}
		
		return gender;
	}
	
	
	
	
	
public String CheckValidEmail(Scanner s) {
	
	s.nextLine();
	
	RegistLogIn rl = new RegistLogIn();
	
	
		
		String s1;
		boolean having = false;

		System.out.println("Enter your Email_id : ");

		while (!having) {

			s1 = s.nextLine();
			//s.nextLine();

			if (s1.matches(rl.getEmailFormt())) {
				email = s1;
				having = true;

			} else if (!s1.matches(rl.getEmailFormt())) {

				System.out.println("Invalid email format.! ,Maintain Email format as mentioned in rules.");
				System.out.println("Enter your Email_id : ");

			}

		}

		return email;

	}
		
	private String CheckValidMobileNumber(Scanner scanner) {

		String regex = "^[0-9]*$";
		boolean found = false;

		System.out.println("Enter Mobile Number : ");

		while (!false) {

			try {
				mobile_no = scanner.nextLine();
				if (mobile_no.matches(regex)) {
					int length = mobile_no.length();

					if (length <= 10) { // string index start from 1 
						found = true;
						return mobile_no;
					} else {
						System.out.println("Mobile Number Should have 10 numbers...");
						System.out.println("Enter Mobile Number : ");
					}
				} else {
					System.out.println("Not a number....");
					System.out.println("Enter Mobile Number : ");
				}
			} catch (Exception e) {
				System.out.println("Incorrect Mobile Number...");
				scanner.nextLine();
				System.out.println("Enter Mobile Number : ");
			}
		}
	}
	
	
	
	public static void main(String[] args) {
//		UserBooking ub = new UserBooking();
//		End_UserMenu enduser = new End_UserMenu();
//		

	}



	 void addBooking(UserBooking ub, End_UserMenu eum, String bookNo, String user ) throws SQLException {
		// TODO Auto-generated method stub
		
		String passengerName = ub.passenger_name;
		int age = ub.age;
		String gender	= ub.gender;
		int busID = eum.getBus_id();
		int seatNo = ub.getPassSeatNo();
		LocalDate date = eum.getDate();
		
		java.sql.Date sqldate = Date.valueOf(date);
		
		
		
		
		
		int bus_No =0;
		String bus_Type="";
		String boarding_point ="";
		String dropping_point="";
		String travel_date_time ="";
		String arriving_date_time="";
		
		
		String getbus = "select buslist.bus_No, buslist.Type, buslist.From_terminal, buslist.To_Terminal, concat(date(sop.schedule_operating_date_time),' | ',time(sop.schedule_operating_date_time)) as from_date_time, " +
				" concat(date(sop.schedule_reaching_date_time),' | ',time(sop.schedule_reaching_date_time)) as to_date_time from bus_list as buslist join schedule_of_bus as sop on buslist.id = sop.bus_No "
				+ "where buslist.bus_No = '" + busID + "' and date(sop.schedule_operating_date_time) = '" + sqldate +"'";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(getbus);
		
		while(rs.next()) {
			bus_No = rs.getInt(1);
			bus_Type = rs.getString(2);
			boarding_point = rs.getString(3);
			travel_date_time = rs.getString(5);
			dropping_point =  rs.getString(4);
			arriving_date_time = rs.getString(6);
		}
		
		
		
		
		
		String addbooking = "insert into Booking_records (booking_no,User_name,passenger_name,age,gender,bus_No,bus_Type,seat_No,boarding_point,travel_date_time,dropping_point,arriving_date_time) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pst = con.prepareStatement(addbooking);
		
		
//		java.sql.Date sqldate = Date.valueOf(travelDate);
//		pst.setDate(5, sqldate);
		
		pst.setString(1, bookNo);
		pst.setString(2, user);
		pst.setString(3,passengerName);
		pst.setInt(4, age);
		pst.setString(5, gender);
		pst.setInt(6, bus_No);
		
		pst.setString(7, bus_Type);
		pst.setInt(8, seatNo);
		pst.setString(9,boarding_point);
		pst.setString(10, travel_date_time);
		pst.setString(11, dropping_point);
		pst.setString(12, arriving_date_time);
		
		pst.executeUpdate();
		
	}
	
	
	public static int getBusNoDAO(int busid) throws SQLException {
		
		String getBus = "select bus_No from bus_list where id = " + busid +"";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(getBus);
		
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}



	public static double PriceDetails(int bus_id, int psngerCount) throws SQLException {
		// TODO Auto-generated method stub
		
		String getBus = "select fare from bus_fare join bus_list on bus_list.id = bus_fare.fare_id where bus_list.bus_No = " + bus_id +"";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(getBus);
		
		while(rs.next()) {
			double ticketprice = psngerCount * rs.getInt(1);
			return ticketprice;
		}
		return 0;
		
	}
	
	public static double PriceDetails(int bus_id) throws SQLException {
		// TODO Auto-generated method stub
		
		String getBus = "select fare from bus_fare join bus_list on bus_list.id = bus_fare.fare_id where bus_list.bus_No = " + bus_id +"";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(getBus);
		
		while(rs.next()) {
			 
			return  rs.getInt(1);
		}
		return 0;
		
	}
	
	
	
	



	public int PaymentOption(Scanner s, double priceDetails) {
		double busfare = priceDetails;
		boolean present = false;
		int option = 1;

		String upiFormat = "^[a-zA-Z0-9.-]{2,256}@[a-zA-Z][a-zA-Z]{2,64}$";

		// can be your name, the first half of your email address, or even your mobile
		// number, and '@ybl' is the initials of your bank

		while (!present) {

			System.out.println("Enter UPI ID : ");

			String UPI_ID = s.nextLine();

			if (UPI_ID.matches(upiFormat)) {
				System.out.println("\nTotal Bus Fare : " + busfare);

				System.out.println("\nPress 1 to continue payment | Press 2 to exit : ");

				try {

					while (option == 1) {

						option = s.nextInt();

						if (option == 1) {
							System.out.println("Payment Successful.....");
							present = true;
							return 1;
						}

						else if (option < 1 || option > 2) {

							System.out.println("Invalid option ....");
							option = 1;

						}

						else {
							// System.out.println("\n Exit Payment");
							present = true;
						}
					}
				} catch (Exception e) {
					System.out.println(e);
					s.nextLine();

				}

			} else
				System.out.println("Invalid UPI ID.....");
			// s.nextLine();}

		}
		return 0;

	}

	
	
	
	

}

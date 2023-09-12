package busResv;
import java.util.*;
import java.util.jar.Attributes.Name;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
public class PassengerBooking {
	String passengerName;
	int busNo;
//	int seatNo;
	Date date;
	
	PassengerBooking() {
		Scanner scanner = new Scanner(System.in);
		CheckingValidName(scanner);
		CheckingValidBusNo(scanner);
//		try {
//			ValidateSQLDate(scanner);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println("Enter seat number : ");
//		seatNo = scanner.nextInt();
//		if (seatNo < )
//		
		CheckValidDate(scanner);

		
		
	}
	
	public String CheckingValidName(Scanner s) {
		
		System.out.println("Enter name of passenger : ");
		passengerName = s.nextLine();
		
		while(!passengerName.matches(("^[a-zA-Z.]*$"))) { // runs loop upto passName not matches pattern
			
			System.out.println("------------------------------------------");
			
			System.out.println("Invalid Name....! \nName must not has : \nNumbers, \nSpecial_Characters, \nSpace");
			
			System.out.println("-----------------------------------------");
			
			System.out.println("Enter the correct name : ");
			passengerName = s.nextLine();
			
			}
	
		//if (passengerName.matches("^[a-zA-Z.]*$"))
		    
			return passengerName;
			
		
	}
	
	
	public int CheckingValidBusNo(Scanner input) {
		

		boolean notInt = false;
		
		
        while(!notInt) {
		try {
		System.out.println("Enter the bus number : ");
		busNo = input.nextInt();
		if(busNo > 0 && busNo < 5) { 
			notInt = true;
			return busNo;
		}
		else
			System.out.println("Invalid Bus Number..!, Choose appropriate bus number form menu");
//			System.out.println("Enter the bus number : ");
//			busNo = input.nextInt();
			notInt = false;
			
		}
		catch(Exception e) {
			System.out.println("Invalid Data...!,Enter a Valid Bus Number");
			input.nextLine();
			notInt = false;
			
		}
		
//		System.out.println("Enter the bus number : ");
//		busNo = input.nextInt();


		}
        
		return busNo;
	}	
	
	
	
	public Date CheckValidDate(Scanner scanner) {
	
		boolean CorrDateFormt = false;
		String pattern = "^(0[1-9]|1\\d|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-\\d{4}$";

		while (!CorrDateFormt) {

			System.out.println("Enter Travel Date in this format : dd-mm-yyyy");

			String dateInput = scanner.next();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			if (dateInput.matches(pattern)) {
				try {
					date = dateFormat.parse(dateInput);
					CorrDateFormt = true;
					return date;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
//					System.out.println("Invalid date format , Use the date format mentioned in rules.");
//					CorrDateFormt = false;
					
				}
				
			}

			else
				System.out.println("Invalid date format , Use the date format mentioned in rules.");
				CorrDateFormt = false;
			
		}

		return date; // if break add in else stmt return empty Null value date shows NUllPOINTEREXCEPTION
	}
	
	
	public boolean isAvailable() throws SQLException{
		
		BusDatabaseAccessObject busdao = new BusDatabaseAccessObject();
		
		BookingDAO bookingdao = new BookingDAO();
		
		int capacity = busdao.getCapacity(this.busNo);
		
		
		
		int booked = bookingdao.getBookedCount(busNo,date);
		
		
		return booked<capacity?true:false;
		
	}
}

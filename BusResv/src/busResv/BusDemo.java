
package busResv;

import java.util.Scanner;

public class BusDemo {

	public static void main(String[] args)  {
		
		int userOpt = 1;
		
		boolean present = false;
		
		
		
		try {
			System.out.println("Bus info Panel : \n");
			BusDatabaseAccessObject.displayBusInfo();
		
		Scanner scanner = new Scanner(System.in);
		
		while (userOpt == 1) {
			
			System.out.println("OPTIONS : \n opt 1 :  Booking \n opt 2 :  Exit \n--------------------------------------------");
			System.out.println("Select choice : ");
			present = scanner.hasNextInt();
			System.out.println("Scanned Input is a Integer ? : " + present);
			
			if (present) { // if true scan the input
				userOpt = scanner.nextInt();

				if (userOpt == 1) {
					
					System.out.println("------------x---------------------x---------");
					System.out.println("Booking Panel : \n");
					
					
					PassengerBooking booking = new PassengerBooking();
					
					System.out.println("-----------x----------------------x---------");
					
					if (booking.isAvailable()) {
						BookingDAO bookdao = new BookingDAO();
						bookdao.addBooking(booking);
						System.out.println("Your booking is confirmed");
						System.out.println("-----------x----------------------x---------");
					} else
						System.out.println("Sorry. Bus is full. Try another bus or date.");

				}
				else if (userOpt < 1 || userOpt > 2) {
					System.out.println("Invalid option !..Try again...");
					userOpt = 1;
					
				}
				else {
					System.out.println("Thank you for using our service..");
					break;
				}
			} 
			else
				System.out.println("Invalid option..!, Choose appropriate option form menu");
				scanner.nextLine();
		}
		scanner.close();

	}
		
		catch(Exception e) {
			System.out.println(e);
			
		}
	}
	
}



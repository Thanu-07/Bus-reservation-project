package dashboardandfunctionalities;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import Admindashboardfunctionalities.AdminFunction;
import registeranglogin.RegistLogIn;


public class UserMenu {

	public static void main(String[] args) {
	
		RegistLogIn rl = new RegistLogIn();
		
		Scanner s = new Scanner(System.in);
		
		
		int option = 1;
		
		while (option == 1) {

			try {

				System.out.println("Enter option : \n\n1) Register  \n2) Log In \n3) Exit");

				System.out.println("\nEnter option :");

				option = s.nextInt();

				if (option == 1) {

					// registration :
					
					s.nextLine();

					rl.ValidEmail_Id(s);
					rl.ValidPassword(s);
					rl.ValidUsername(s);

					RegistLogIn.isValidRegistration(rl);

					option = 1;
				}

				else if (option == 2) {

					// log In
					
					s.nextLine();

					String user = rl.LogIn(s);

					if (!user.equals("")) {


						while (true) {
							
							System.out.println("\nMenu :\n*------------------------*----------------------*");
							
							System.out.println("\nChoice : (1) Search Bus \nChoice : (2) Booking History \nChoice : (3) Cancel Booking \nChoice : (4) Exit ");

							System.out.println("\nEnter choice :");

							int choice = s.nextInt();

							End_UserMenu eum = new End_UserMenu();

							if (choice == 1) {
								
								s.nextLine();

								// user menu options :

//				End_UserMenu eum = new End_UserMenu();

//		eum.CheckValidBoardPoint(s);
//
//		eum.CheckValidDropPoint(s);
//
//		eum.CheckValidTravelDate(s);

								// search bus panel:

								String busid = eum.SearchBus(s, eum.CheckValidBoardPoint(s), eum.CheckValidDropPoint(s),
										eum.CheckValidTravelDate(s));

								//System.out.println("Bus id : " + busid);
								
								eum.getBusId(busid);
								
								System.out.println("");
								
								if(!busid.equals("No bus available...")) {
								

								if (eum.isAvailable()) {

									userMenuDAO ud = new userMenuDAO();
									
									
									
									Object[] in = eum.selectSeat(s, ud.getCapacity(eum.getBus_id())).toArray();

									ArrayList<UserBooking> passcount = new ArrayList<>();
									
									UserBooking ub = new UserBooking();
									
									for(int i=0;i<in.length;i++) {
									
									eum.setBusSeat((int)in[i]);
									
									
									UserBooking ubb = new UserBooking();
									
									s.nextLine();
									
									ubb.CheckingValidName(s);
									ubb.CheckValidGender(s);
									ubb.CheckingValidAge(s);
									ubb.setPassSeatNo(eum.getBusSeat());
									
									passcount.add(ubb);
									// note : reading input String after int need to gave s.nextLine(); reading input String before getting input as int no need
									
								
									
									}
									
									System.out.println("\nTicket Price Details : " + UserBooking.PriceDetails(eum.getBus_id()) + " * " + passcount.size());
									
									System.out.println("\nTotal Ticket Price : " + UserBooking.PriceDetails(eum.getBus_id(),passcount.size()) +"\n");
									
									
									while(true) {
									System.out.println("Select 1 to proceed Payment, Select 2 to exit :\n");
									System.out.println("Select Option : ");
									
									try {
									
									int payment = s.nextInt();
									
									
									if(payment == 1) {
									
										s.nextLine();
									
									int paid = ub.PaymentOption(s, UserBooking.PriceDetails(eum.getBus_id(),passcount.size()));
									
									
									if(paid == 1) {
									
									System.out.println("");
									
									
									//ArrayList<String> booknos = new ArrayList<String>();
									
									GenerateEticket eticket = new GenerateEticket();
									
									for(int i=0;i<passcount.size();i++) {
										
										//System.out.println(passcount.get(i));
										
									
									GenerateBookNo gb = new GenerateBookNo();
									String bookNo = GenerateBookNo.generateBookNo(gb.getLength());

									while (GenerateBookNo.CheckBookNo(bookNo)) {
										bookNo = GenerateBookNo.generateBookNo(gb.getLength());
									}
									
									
									
									
									ub.addBooking(passcount.get(i), eum, bookNo,user);
									
									eticket.generateTicket(bookNo);

									//ub.addBooking(ub, eum, bookNo);
				
									System.out.println("");
									
									
									//booknos.add(bookNo);
									
									}
									
//									GenerateEticket eticket = new GenerateEticket();
//									
//									
//									
//									eticket.generateTicket(booknos);
//									
									
									
									//option = 3; -- use when show ticket and exit app
									
									
									break;
									
									}
									
									else
									System.out.println("Payment Unsuccessful....\n");
									
									
						
									}
									else if(payment < 1 || payment >2) {
										System.out.println("Invalid option..");
									}
									else
										{System.out.println("Exit Payment\n");
										//option =3;
									break;}
									
									}
									
									catch(InputMismatchException i) {
										System.out.println("Not a valid Payment Option...");
										s.nextLine();
										
									}
									
									}
									
								
								}
								
								//break; -- commenting this helps to repeating menu

								}
									
								

							}
							
							else if (choice == 2) {
								
								
								
							ArrayList<String> getbookhis= AdminFunction.BookingHistory(user);
							
							if(!getbookhis.isEmpty()) {
								
								System.out.println("Booking History : \n");
								
								for(String str : getbookhis) {
									System.out.println(str + "\n");
								}
							}
							else 
//								
								System.out.println("No Booking History found...");
								
							
							}
							
							else if (choice == 3) {
								
								s.nextLine();
								
								System.out.println("\nEnter Booking Number : ");
								
								String bookno = s.nextLine();
								
								try {
									
								int num =Integer.parseInt(bookno);
								
								num = AdminFunction.BookingCancellation(bookno);
								
								if(num != 0) {
									
									int getbusfare = AdminFunction.getBusFromID(num);
									
									System.out.println("Ticket cancellation request in process...");
									
									AdminFunction.requestCancelTicket(num);
									
									
									AdminFunction.refundTicketFare(getbusfare);
									
									
								
								}
								else
									System.out.println("No Booking found ...");
								
								}
								
								catch(NumberFormatException e) {
									//System.out.println(e.getMessage());
									e.printStackTrace();
									System.out.println("Not a valid booking number...");

								}
								
								
							}
							
							

							else if (choice > 4 || choice < 1) {
								System.out.println("Incorrect Option..");
							

							} else {
								System.out.println("Exit Menu\n");
								option = 1;
								break;
							}
						}

						//option = 1;

					}

					else
					
						{System.out.println();
						option = 1;}
					
					

				} 
				
				else if (option < 1 || option > 3) {
					System.out.println("Incorrect Option..\n");
					option = 1;
				} 
				
				else 
					System.out.println("Exit");
					
				

			} 
			
			catch(InputMismatchException i) {
				System.out.println("Not a Valid Option...\n");
				s.nextLine();
			}
			
			
			catch (Exception e) {
				e.printStackTrace();
				s.nextLine();
				//System.out.println("Not a Valid Option...\n");
				//option = 1;
			}

		}
	}

}

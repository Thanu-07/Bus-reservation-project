package Admindashboardfunctionalities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import busResv.DatabaseConnection;

import registeranglogin.AdminRegLogIn;

import dashboardandfunctionalities.End_UserMenu;



public class AdminMenu {

	public static void main(String[] args) {
		
		int option = 1;
		
		AdminRegLogIn admin = new AdminRegLogIn();
		
		Scanner s = new Scanner(System.in);
		
		
		
		
		while(option ==1) {
			
			try {
				
				System.out.println("Enter option : \n\n1) Register  \n2) Log In \n3) Exit");
			
			System.out.println("\nEnter option :");
			
			option = s.nextInt();
		
		
		if (option == 1) {
		
			s.nextLine(); // first scanning number as option then we scan string after scan number it stores enter key as next Line character where we scan string
		
		admin.ValidEmail_Id(s);
		admin.ValidPassword(s);
		admin.ValidUsername(s);
		
		AdminRegLogIn.isValidRegistration(admin);
		option = 1;
		
		}
		
		else if(option == 2) {		
			
			s.nextLine();
			String user = admin.LogIn(s);
			if(!user.equals("")) {
				
				// show menu :
				
				while(true) {
				
				System.out.println("\nMenu      \n*------------------------*----------------------*");
				
				System.out.println("1) Add Bus \n2) Remove Bus \n3) Update Bus \n4) Manage User Accounts \n5) Exit Menu");
				
				System.out.println("\nEnter choice :");
				
				int choice = s.nextInt();
				
				
				if(choice >= 1 && choice <= 5) {
				
				
				AddBus addbus = new AddBus();
				
				switch(choice) {
				
				
				case 1 :
					
					//1) add new Bus :
					
					System.out.println("\nAdd Bus Panel : \n*------------------------*----------------------*");
					
					//AddBus addbus = new AddBus();
					
//					addbus.addNewBusNo(s);
//					addbus.addNewBusType(s);
//					addbus.addNewBusCapcity(s);
//					addbus.addNewBusBp(s);
//					addbus.addNewBusDp(s);
					
					AddBus.addBus(addbus.addNewBusNo(s),addbus.addNewBusCapcity(s),addbus.addNewBusType(s),addbus.addNewBusBp(s),addbus.addNewBusDp(s));
					
				break;
				
				case 2 : 
					
					//	2) remove old bus(if exists) :
					
					System.out.println("\nRemove Bus Panel : \n*------------------------*----------------------*");
					
					AddBus removebus = addbus;
					End_UserMenu eu = new End_UserMenu();
					
					AddBus.getIDremoveBus(removebus.GetBus(s)); // using busNo
					ArrayList<Integer> bus = AddBus.getIDremoveBus(eu.CheckValidBoardPoint(s),eu.CheckValidDropPoint(s));
					
					// using bp and dp
					
					if(bus.isEmpty()) {
						System.out.println("Bus Board or Drop Point not found....");
						
					}
					
					break;
					
					
				case 3 :
					
					// 3) update bus :
					
					System.out.println("\nUpdate Bus Panel : \n*------------------------*----------------------*");
					
					UpdateBus ub = new UpdateBus();
					
					ub.updateBusDetails(s);
					ub.updateBus(ub.GetBusID(s));
					
					
					
					break;
					
					
				case 4 :
					
					//	4) manage user accounts :
					
					System.out.println("\nManage User Account Panel : \n*------------------------*----------------------*");
					
					AdminFunction af = new AdminFunction();
					
					int Accoption = 1 ;
					
					
					
					while(Accoption == 1) {
						
						
						System.out.println("\nOptions : \n1) Show All User Details \n2) Show User By ID \n3) Exit");
					
					System.out.println("\nEnter options : ");
					
					try {
					
					 Accoption = s.nextInt();
					
					if(Accoption == 1) {
					
						AdminFunction.showUser();
						Accoption =1;
					
					}
					else if(Accoption == 2) {
						
						AdminFunction.checkUser(s);
						Accoption =1;
					}
					
					else if(Accoption > 3 || Accoption < 1) {
						System.out.println("Incorrect option...");
						Accoption = 1;
					}
					
					else
						
						{System.out.println("Exit Manage User Account Panel\n");
						break;}
					}
					
					catch(Exception e) {
						s.nextLine();
						System.out.println("Invalid number...");
						Accoption = 1;
					}	
					}
					
					break;
					
				
//				case 5 :
//					System.out.println("Exit menu...\n");
//				break;
				
				
//				default :
//					System.out.println("Invalid Choice....\n");
//				break;
				
				}
				if(choice == 5) {
					System.out.println("Exit menu...\n");
					break;
				}
			}
				else
					System.out.println("Invalid Choice....");	
				
			
				}
				
				option =1;
			}
			
			
			else
				{System.out.println("Admin not found...");
				option = 1;}
			
			}
			
			
		
		
		else if (option < 1 || option > 3) {
			System.out.println("Incorrect Option..");
			option =1;
		}
		else
			{System.out.println("Exit");
			break;}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			s.nextLine();
			option = 1;
		}

	}
	
	}
	
}

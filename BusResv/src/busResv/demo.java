package busResv;


import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import registeranglogin.RegistLogIn;



public class demo{
	public static void main (String[] args) {
		
		int option = 1;
		
		boolean found = false;
		
		Scanner s = new Scanner(System.in);
//		try {
//			demo.GenertBookingNo();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//demo.genertNum(0);
		RegistLogIn rl = new RegistLogIn();
		
		System.out.println("Option : \n 1) Register \n 2) LogIn \n 3) Exit");
		
//		Note : s.nextInt() method reads the next integer from the input stream, but it does not consume the newline character (Enter key) that is pressed after entering the integer value.
		
		while (option == 1) {
			
			System.out.println("Enter Option : ");
			try {
			option = s.nextInt();
			}catch (InputMismatchException e) {
				s.nextLine();
				option = 4;
				
			}
		
		if(option == 1) {
		
	
		rl.ValidEmail_Id(s);
		rl.ValidPassword(s);
		rl.ValidUsername(s);
		RegistLogIn.isValidRegistration(rl);
		option =1;
		
		}
		
		else if(option == 2) {

			try {
				s.nextLine();
				rl.LogIn(s);
				option = 1;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
			
		else if(option < 1 || option > 3) {
			System.out.println("Invalid Option...");
			
			option =1;
		}
		else
			System.out.println("Thank you Using our App...........................");
			
		}
		s.close();
	}
	


	
	public static void GenertBookingNo() throws SQLException {
		int num =0,i=0;
		ArrayList ar2  = demo.BookingNoGenerate(10,1,10); // 1-10 exclusive 1,(10-1) = (1,9)
		while (i<ar2.size()) {
		 //System.out.println("TNCBOOK0"+ ar2.get(i));
			 num = (int) ar2.get(i);
			 //System.out.println(num);
			addBookingNo("TNCBOOK0" + num);
			i++; 
		}
		//System.out.println(genertNum (num));
	}
	
//  Using for loop : 	
	
//	for (int i = 0;i<ar2.size();i++) {
//		 //System.out.println("TNCBOOK0"+ ar2.get(i));
//			 num =  (int) ar2.get(i);
//			 System.out.println(num);
//		}
	
//	private static String genertNum(int n) {
//		// TODO Auto-generated method stub
//		
//		String str;
//		str = "TNCBOOK0" + n;
//		
//		return str;
//		
//	}

	public static ArrayList BookingNoGenerate(int size,int min,int max) {
	
		ArrayList ar = new ArrayList(); 
		
		Random rd = new Random();
		
		while (ar.size() < size) {
		int random =rd.nextInt((max - min) +1)  + min; // 1 to 11 exclusive , 1 -10 inclusive
			//int random = rd.nextInt(min,max);
		if(!ar.contains(random)) {
			ar.add(random);
		}
		
		}
		
		return ar ;
	}
	
	public static void addBookingNo(String s) throws SQLException {
		
		String query = "insert into generate_book_no (booking_no)values ('"+ s +"');";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement pst = con.createStatement();
		// pst.setString(1, s);
		int rows = pst.executeUpdate(query);
		System.out.println("No of rows inserted : " + rows);
		
	} 
}

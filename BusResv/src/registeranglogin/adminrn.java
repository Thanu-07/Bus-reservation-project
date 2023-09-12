package registeranglogin;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class adminrn{
	
	String values = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	char c;
	int num;	
	

	HashSet<String> in = new HashSet<>();
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int option =1;
//		
//		adminrn a = new adminrn();
//	
//		for(int i=0;i<10;i++) { // generate 10 random strings
//			String s1;
//			try {
//				s1 = a.getUniqueRandomNumber();
//				System.out.println(s1);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		AdminRegLogIn arl = new AdminRegLogIn();
		
		
		System.out.println("Option : \n 1) Register \n 2) LogIn \n 3) Exit");
		
		
		while(option == 1) {
			
			System.out.println("Enter option : ");
			
			
				
		try {
			
			option = s.nextInt();
		}
		catch (InputMismatchException e) {
			option = 4;
			
		}
		
		if (option == 1) {
			
			s.nextLine();
			
		arl.ValidEmail_Id(s);
		arl.ValidUsername(s);
		arl.ValidPassword(s);
		
		
		
		AdminRegLogIn.isValidRegistration(arl);
		option =1;
		}
		
		else if (option ==2) {
		try {
			s.nextLine();
			arl.LogIn(s);
			option =1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else if(option < 1 || option > 3) {
		System.out.println("Invalid Option...");
		s.nextLine(); // handling error using nextLine character
		option = 1;
	}
	else
		System.out.println("Thank you Using our App...........................");
		
		
		}
			
		
		
	}	
	
	public String generateRandomNumber(int len) {	
		
		
		StringBuilder sb = new StringBuilder();
		
		Random rd = new Random();
		
		for (int i=0;i<len;i++) {
			
			num = rd.nextInt(values.length());
			c = values.charAt(num);
			sb.append(c);
			
		  
		}
		return sb.toString();
		
	}
//	public String getUniqueRandomNumber(){
//		
//		
//		String randomKey = generateRandomNumber(8);
//		
//		while(in.contains(randomKey)) {
//			randomKey = generateRandomNumber(8);
//			
//		}
//		in.add(randomKey);
//		return randomKey;
//	}
	
	
	public String getUniqueRandomNumber() throws SQLException{
		
		
		String randomKey = generateRandomNumber(8);
		
		while(AdminRegLogInDAO.CheckKeyFound(randomKey)) {
			randomKey = generateRandomNumber(8);
			
		}
		if (AdminRegLogInDAO.MaxAdminKey()) {
		AdminRegLogInDAO.InsertCheckedKey(randomKey);
		}
		return randomKey;
	}
	
	
	
}


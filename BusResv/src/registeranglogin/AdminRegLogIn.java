package registeranglogin;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminRegLogIn extends RegistLogIn {

	private String key ;
	
	
	
	
	
	public String ValidEmail_Id(Scanner s) {
		
		String s1;
		boolean having = false;

		System.out.println("Enter your Email_id : ");

		while (!having) {

			s1 = s.nextLine();
			//s.nextLine();

			if (s1.matches(emailFormt)) {
				setEmail_id(s1);
				having = true;

			} else if (!s1.matches(emailFormt)) {

				System.out.println("Invalid email format.! ,Maintain Email format as mentioned in rules.");
				System.out.println("Enter your Email_id : ");

			}

		}

		return getEmail_id();

	}
	
	

	public String ValidPassword(Scanner s) {

		String ss;
		boolean found = false;
				
		System.out.println("Enter your Password : ");

		

		while (!found) {
			
			ss = s.nextLine();
			
			if (ss.length() >= 8 && ss.length() <= 20) {
				if (ss.matches(passwordFormt)) {
					setPassword(ss);
					found = true;
					// return Password;
				} else {
					System.out.println("Password should has Upper and Lower case letter & number & special character..! ,Not contains spaces");
					System.out.println("Enter your Password : ");
				}

			} else 
				{System.out.println("Password must has minimum of 8 and maximum of 20 characters.");
				System.out.println("Enter your Password : ");}
			}

		return getPassword();
	}
	
	public String ValidUsername(Scanner s) {

		String sg;
		boolean present = false;

		System.out.println(
				"Note : UserName must has : \n 1) UpperCase letter \n 2) LowerCase letter \n 3) Numbers \n 4) Underscore (Optional)");
		System.out.println("Enter your Username : ");
		

		while (!present) {
			
			sg = s.nextLine();

			if (sg.length() >= 5) {

				if (sg.matches(userNameFormt)) {
					setUserName(sg);
					present = true;
				} else 
					{System.out.println("UserName should has one Upper and Lower case letter & number"); // or '_'
					System.out.println("Enter your Username : ");}

			} else 
				{System.out.println("User Name must has above 5 characters..");
				System.out.println("Enter your Username : ");}
			
		}

		return getUserName();
	}
	
	

	public static void isValidRegistration(AdminRegLogIn a) {

		try {
			if (AdminRegLogInDAO.VerifyRegEmailDAO(a.getEmail_id()) &&
					AdminRegLogInDAO.VerifyRegUserDAO(a.getUserName())) {
				
																					
					AdminRegLogInDAO.InsertCheckedRecord(a);
					
					final String key = AdminRegLogInDAO.GenerateNewKey(a.getUserName());
					System.out.println("\nUse Key to Log_In : \n*-------------------*--------------------*\nyour Key is : " + key);

					System.out.println("Registration successful.....");

			}
			else
				System.out.println("Email ID or User already exists....Try to Log In");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		AdminRegLogIn arl = new AdminRegLogIn();
		arl.ValidEmail_Id(input);
		arl.ValidUsername(input);
		arl.ValidPassword(input);
		AdminRegLogIn.isValidRegistration(arl);
		
		try {
			arl.LogIn(input);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	// Admin Log_In Process : 
	
	
	public String LogIn(Scanner s) throws SQLException {
		
		String emilorusrnme,password;
		boolean b = false;
		
		System.out.println("Enter Email ID |OR| User Name : ");
		emilorusrnme = s.nextLine();
		System.out.println("Enter Password :");
		password = s.nextLine();
		System.out.println("Enter Key : ");
		key = s.nextLine();
		
		
		
		
		while(!b) {
			
					
				
			final String OrgUser = AdminRegLogInDAO.ValidateLogInUser(emilorusrnme);

			if (!OrgUser.equals("")) {

				final String OrgPswd = AdminRegLogInDAO.ValidateLogInPassword(OrgUser);
				final String OrgKey = AdminRegLogInDAO.getAdminKey(OrgUser);
				final String UserKey = AdminRegLogIn.ValidateAdminKey(OrgKey,key);
				
					if (OrgPswd.equals(password)) {
						if (!UserKey.equals("")) {
						System.out.println("Welcome Admin : " + OrgUser);
						b = true; // if we wont stop the loop it scans next line from user and stored it into password we ask in loop starting and if doent match the password throws error
						return OrgUser;
						
						} 
						else 
							{System.out.println("Invalid Key......");
							System.out.println("Enter Key : ");
							key = s.nextLine();}
						
					}else 
							{System.out.println("Incorrect Password....");
							System.out.println("Enter Password : ");
							password = s.nextLine();}
						
			} else
				{System.out.println("User Not found...");
				b = true;}					
		}
		
		return "";
	}



	private static String ValidateAdminKey(String orgKey,String userEnterKey) {
		// TODO Auto-generated method stub
		
		if(userEnterKey.equals(orgKey)) {
			return userEnterKey;
		}
		return "";
		
	}
	
}

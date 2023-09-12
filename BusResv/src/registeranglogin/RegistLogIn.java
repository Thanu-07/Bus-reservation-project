package registeranglogin;

import java.util.Scanner;



import java.sql.*;

public class RegistLogIn {
	
	private String Email_id;
	private String Password;
	private String UserName;
	
	
	
	
	
	 public String getEmail_id() {
		return Email_id;
	}




	public String getPassword() {
		return Password;
	}




	public String getUserName() {
		return UserName;
	}

	 final String emailFormt = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	 final String passwordFormt = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
	 final String userNameFormt = "^(?=.*[a-z])(?=.*[A-Z])(?=.*_)?(?=.*\\d)[a-zA-Z_\\d]{5,20}$";
	 
	 
	 public void setEmail_id(String email_id) {
		Email_id = email_id;
	}




	public void setPassword(String password) {
		Password = password;
	}




	public void setUserName(String userName) {
		UserName = userName;
	}

	
	 public String getEmailFormt() {
			return emailFormt;
		}
	
	
	
	
	
	public String ValidEmail_Id(Scanner s) {

		String s1;
		boolean having = false;

		System.out.println("Enter your Email_id : ");

		// Scanner.next() method reads the input up to the next whitespace, and your
		// input "12.12@34.com" contains a dot, which is considered as a delimiter by
		// the next() method. As a result, it only reads "12.12@34.com" and considers
		// "12.12" as one token and "34.com" as the next token.
		// s1= s.nextLine();

		while (!having) {

			s1 = s.next();
			s.nextLine();

			if (s1.matches(emailFormt)) {
				Email_id = s1;
				having = true;

			} else if (!s1.matches(emailFormt)) {

				System.out.println("Invalid email format.! ,Maintain Email format as mentioned in rules.");
				System.out.println("Enter your Email_id : ");

			}

		}

		return Email_id;

	}

	public String ValidPassword(Scanner s) {

		String ss;
		boolean found = false;

		System.out.println(
				"Note : Password must has \n 1) UpperCase letter \n 2) LowerCase letter \n 3) Numbers \n 4) Special_Character.");
		System.out.println("Enter your Password : ");

		

		while (!found) {
			ss = s.nextLine();
			
			if (ss.length() >= 8) {
				if (ss.matches(passwordFormt)) {
					Password = ss;
					found = true;
					// return Password;
				} else {
					System.out.println("Invalid Password..! Create password as per rules ..!");

					System.out.println("Enter your Password : ");
					ss = s.nextLine();
				}

			} else {
				System.out.println("Password must has minimum of 8 characters & Not contains spaces.");

				System.out.println("Enter your Password : ");
				ss = s.nextLine();
			}

		}
		return Password;
	}

	public String ValidUsername(Scanner s) {

		String sg;
		boolean present = false;

		System.out.println(
				"Note : UserName must has : \n 1) UpperCase letter \n 2) LowerCase letter \n 3) Numbers "); // \n 4) Underscore (Optional)
		System.out.println("Enter your Username : ");
		sg = s.nextLine();

		while (!present) {

			if (sg.length() >= 5) {

				if (sg.matches(userNameFormt)) {
					UserName = sg;
					present = true;
				} else {
					System.out.println("Maintain username format as mentioned in rules.");

					System.out.println("Enter your Username : ");
					sg = s.nextLine();
				}

			} else {
				System.out.println("User Name must has atleast 5 characters..");

				System.out.println("Enter your Username : ");
				sg = s.nextLine();
			}
		}

		return UserName;
	}

//	public static void main(String[] args) {
//
//		Scanner s = new Scanner(System.in);
//
//		RegistLogIn rl = new RegistLogIn();
//
////		System.out.println(rl.ValidEmail_Id(s));
//
//		rl.ValidEmail_Id(s);
//		rl.ValidPassword(s);
//		rl.ValidUsername(s);
//		
////		System.out.println("Email ID  : " + s1 + " Password : " + s2 + " UserName : " + s3);
//		// isValidRegistration(rl);
//		try {
//			rl.LogIn(s);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public static void isValidRegistration(RegistLogIn rl) {
		RegisterDAO rdao = new RegisterDAO();

		try {
			if (RegisterDAO.VerifyRegEmailDAO(rl.Email_id) && RegisterDAO.VerifyRegUserDAO(rl.UserName)) {
				//if (RegisterDAO.VerifyDetailsDAO(rl.Email_id, rl.Password, rl.UserName)) { // rl.getEmail_id(),
																							// rl.getUserName()
					RegisterDAO.InsertCheckedRecord(rl);

					System.out.println("Registration successful.....");
				//} 
//				else
//				System.out.println("Email ID or User already exists....Try to Log In");
			}
			else
				System.out.println("Email ID or User already exists....Try to Log In");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public String LogIn(Scanner s) throws SQLException {
		
		String emilorusrnme;
		boolean b = false;
		System.out.println("Enter Email ID |OR| User Name : ");
		emilorusrnme = s.nextLine();
		System.out.println("Enter Password : ");
		
		
		
		while(!b) {
			
			Password = s.nextLine();
					
				
			final String OrgUser = RegisterDAO.ValidateLogInUser(emilorusrnme);

			if (!OrgUser.equals("")) {

				final String OrgPswd = RegisterDAO.ValidateLogInPassword(OrgUser);

				if (OrgPswd.equals(Password)) {

					System.out.println("Welcome User : " + OrgUser);
					b = true; // if we wont stop the loop it scans next line from user and stored it into password we ask in loop starting and if doent match the password throws error
					return OrgUser;
					
				} 
				
				else {
					System.out.println("Incorrect Password....");
					System.out.println("Enter Password : ");
					
				}

			} else
				{System.out.println("Account/User Not found...");
				b = true;}					
		}
		
		return "";
	}
	

}

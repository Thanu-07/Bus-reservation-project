package registeranglogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import busResv.DatabaseConnection;

public class RegisterDAO {


	static void InsertCheckedRecord(RegistLogIn rl) throws SQLException {
		
		String emid = rl.getEmail_id();
		String pwd = rl.getPassword();
		String usrnme = rl.getUserName();
		

		String query = "insert into User_Info (email_id,pass_word,user_name) values (?,?,?)";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1,emid);
		pst.setString(2, pwd);
		pst.setString(3, usrnme);
		pst.executeUpdate();
		
	}
	
//	static boolean CheckRecord(String eid,String passwd,String un) throws SQLException{
//		
//		boolean add ;
//		
//		String emid = eid;
//		String pwd = passwd;
//		String usrnme = un;
//		
//		String funct = "select count(*) from User_Info where email_id = ? or user_name = ? and pass_word = ?"; 
//;
//		Connection con = DatabaseConnection.getDatabaseConnection();
//		PreparedStatement pst = con.prepareStatement(funct);	
//		
//		pst.setString(1,emid);
//		pst.setString(2, pwd);
//		pst.setString(3, usrnme);
//		ResultSet rs = pst.executeQuery();
//		rs.next();
//			if (rs.getInt(1) > 0) {
//				add = false;
//			}
//			else
//				add = true;
//			
//				return  add;
//		
//	}
	
	static boolean VerifyRegEmailDAO(String email) throws SQLException{
		
		
		String eid = email;
		
		
		String funct = "select user_name,pass_word from User_Info where email_id = ?"; 
;
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(funct);	
		
		pst.setString(1,eid);
		
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return false;
		}
		else
			return  true;
		
	}
	
	static boolean VerifyRegUserDAO(String username) throws SQLException{
		
		
		String user = username ;
		
		
		String funct = "select email_id from User_Info where user_name = ?"; 

		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(funct);	
		
		pst.setString(1,user);
		
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return false;
		}
		else
			return  true;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static String ValidateLogInPassword(String email_username) throws SQLException {
		
		String eid_pwd = email_username;
		//String pwd = password;
		
		
		
		String getPswd = "select pass_word from User_Info where email_id = ? or user_name = ?";
		
		
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(getPswd);
		pst.setString(1,eid_pwd);
		pst.setString(2, eid_pwd);

	
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {
			return rs.getString(1);
		}
		else
			con.close();
			return "";
		
		
		
	}
	
	
	 static String ValidateLogInUser(String eidUser) throws SQLException {
		
		
		String getUser = "select  email_id ,user_name from User_Info where email_id = "+ "'" + eidUser + "'" + " or user_name = " + "'"+ eidUser + "'";
		Connection con = DatabaseConnection.getDatabaseConnection();
		
		Statement st = con.createStatement();
		ResultSet rs1 = st.executeQuery(getUser);
		
		
		if (rs1.next()) {
			 return rs1.getString(2);
		}
		con.close();
		return "";
	}
	 
	 

}

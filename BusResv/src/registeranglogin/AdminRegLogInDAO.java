package registeranglogin;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import busResv.DatabaseConnection;

public class AdminRegLogInDAO extends RegisterDAO{
	
	
//	Note : DatabaseConnection.getDatabaseConnection(); shows error : Default constructor cannot handle exception type SQLException thrown by implicit super constructor. Must define an explicit constructor
//   so DatabaseConnection.getDatabaseConnection(); throws the error to the mrthod which calls this connection the default constructor of the class in which you are trying to call the database connection method
//	 the default constructor of the class in which you are trying to call the database connection method is attempting to call the default constructor of its superclass, which is implicitly provided by Java. 
//	 However, the default constructor of the superclass (Object class) cannot handle the checked exception SQLException thrown by the database connection method.
//	 To resolve this issue, you need to explicitly define a constructor for your class that handles the SQLException. 
	
	
	
	public static void InsertCheckedRecord(AdminRegLogIn a) throws SQLException{
		
		String email = a.getEmail_id();
		String username= a.getUserName();
		String password = a.getPassword();
		
		String query = "insert into Admin (admin_email,admin_Name,admin_Password) values (?,?,?)";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setString(1,email);
		pst.setString(2, username);
		pst.setString(3, password);
		
		pst.executeUpdate();
		
		
	
	}
	
	
	static boolean VerifyRegEmailDAO(String email) throws SQLException{
		
		
		String eid = email;
		
		
		String funct = "select admin_Name from Admin where admin_email = ?"; 
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
		
		
		String funct = "select admin_email from Admin where admin_Name = ?"; 
;
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
	
	
	
	public static String GenerateNewKey(String userName) throws SQLException {
		// TODO Auto-generated method stub
		String query = "select admin_Key from AdminKey as ak join Admin as ad "
				+ "on ak.admin_id = ad.admin_id where ad.admin_Name = '" + userName + "'";
	
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			return rs.getString(1);
		}
		
		return "";
	}
	
	
	
	
	//  Admin Log_In Validation Process : 
	
	
	 static String ValidateLogInUser(String eidUser) throws SQLException {
			
			
		String getUser = "select  admin_email , admin_Name from Admin where admin_email = "+ "'" + eidUser + "'" + " or admin_Name = " + "'"+ eidUser + "'";
		Connection con = DatabaseConnection.getDatabaseConnection();
		
		Statement st = con.createStatement();
		ResultSet rs1 = st.executeQuery(getUser);
		
		
		if (rs1.next()) {
			 return rs1.getString(2);
		}
		return "";
	}	

	
	
	
	
	
	static String ValidateLogInPassword(String email_username) throws SQLException {
		
		String eid_pwd = email_username;
		
		
		
		String getPswd = "select admin_Password from Admin where admin_email = ? or admin_Name = ?";
		
		
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(getPswd);
		pst.setString(1,eid_pwd);
		pst.setString(2, eid_pwd);

	
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {
			return rs.getString(1);
		}
		else
			return "";
		
		
		
	}





	public static String getAdminKey(String username) throws SQLException {
		
		String query = "select admin_Key from AdminKey join Admin on AdminKey.admin_id = Admin.admin_id "
				+ "where Admin.admin_Name = '" + username + "' or Admin.admin_email = '" + username + "'";
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			return rs.getString(1);
		}
	
		
		return "";
	}
	
	
	
	
	// --- checking AdminKey already present in table : AdminKey
	
	public static boolean CheckKeyFound(String randomKey) throws SQLException {
		
		String query = "select admin_id from AdminKey where admin_Key = '" + randomKey + "'";
		
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			return true;
		}
		
		return false;
		
	}


	public static void InsertCheckedKey(String randomKey) throws SQLException {
		
		
		String in = "insert into AdminKey (admin_Key) values ('" + randomKey + "')";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		st.executeUpdate(in);
		
		
		
	}


	public static boolean MaxAdminKey() throws SQLException {
		String query = "select count(*) from AdminKey";
		
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			if(rs.getInt(1) < 10)
			return true;
		}
		
		return false;
	}


	
	}
	
	
	
	
	
	
	
	
	


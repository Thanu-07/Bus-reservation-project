package Admindashboardfunctionalities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import busResv.DatabaseConnection;
import dashboardandfunctionalities.End_UserMenu;

public class AddBus extends AdminFunction{
	
	
//	public static void main(String args[]) {
//		AddBus ab = new AddBus();
//		End_UserMenu eum = new End_UserMenu();
//		
//		Scanner s = new Scanner(System.in);
//		
//		ab.addNewBusNo(s);
//		ab.addNewBusType(s);
//		ab.addNewBusCapcity(s);
//		ab.addNewBusBp(s);
//		ab.addNewBusDp(s);
//		
//		
//		addBus(ab.busNo,ab.capacity,ab.type,ab.from,ab.to);
//		
//		getIDremoveBus(ab.GetBus(s));
//		getIDremoveBus(eum.CheckValidBoardPoint(s),eum.CheckValidDropPoint(s));
//	}
	
	
int addNewBusNo(Scanner s) throws SQLException{
		
	  
	    
	    String query = "select count(*) from bus_list where bus_No = ?";
	    Connection con = DatabaseConnection.getDatabaseConnection();
	    PreparedStatement pst = con.prepareStatement(query);
	    
	    
		
		while (true) {
			System.out.println("Enter New Bus No :");

			try {
				busNo = s.nextInt();
				pst.setInt(1, busNo);
				ResultSet rs = pst.executeQuery();
				rs.next();
				
				if(rs.getInt(1) != 1) {
					break;
				
				}else
					System.out.println("Bus available....");

			} catch (Exception e) {
				System.out.println("Not a valid bus number....");
				s.nextLine();
			}
		}
		
		return busNo;
	}
	
	String addNewBusType(Scanner s){
		
		
		
		while(true) {
			
			System.out.println("Enter Bus Type : ");
			type = s.nextLine();
			if (type.equals("AC:Sleeper") || type.equals("AC") || type.equals("Sleeper")) {
				// type = Type;
				break;
			}
			else
				System.out.println("Incorrect Bus type .....");
		
		}
		
		return type;
		
	}
	
	int addNewBusCapcity(Scanner s){
		
		while(true) {
			
			System.out.println("Enter Bus Capacity : ");
			
			try {
			
				int capc = s.nextInt();
				
				if(capc > 15 && capc < 55) {
					capacity = capc;
					break;
				}
				else
					System.out.println("Bus capacity doesnt below 18 or above 54");
			
			
			}
			catch(Exception e) {
				System.out.println("Not a valid number...");
				s.nextLine();
			}
			
			
			}
		
		return capacity;
		
	}
	
	String addNewBusBp(Scanner s){
		
		End_UserMenu eu = new End_UserMenu();
		
		String bp = eu.CheckValidBoardPoint(s);
		
		if(!bp.equals("")) {
			from = bp;
		}
		
		return from;
		
	}
	
	String addNewBusDp(Scanner s){
		
		End_UserMenu eu = new End_UserMenu();
		
		String dp = eu.CheckValidDropPoint(s);
		
		if(!dp.equals("")) {
			to = dp;
		}
		
		return to;
	
	}

	static void addBus(int busno,int seats,String type,String from,String to) throws SQLException {
		
		String addbus = "insert into bus_list (bus_No,capacity,Type,From_terminal,To_Terminal) values (?,?,?,?,?)";
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(addbus);
		pst.setInt(1, busno);
		pst.setInt(2, seats);
		pst.setString(3, type);
		pst.setString(4, from);
		pst.setString(5, to);
		
		int rows = pst.executeUpdate();
		
//		if(rows == 1) {
//			return " New Bus Added...";
//		}
//		
//		return "Bus already Exits...";
		
		System.out.println("No.of.rows affected : " + rows);
		
		
	}
	
	// removeBus:
	
	
	static void getIDremoveBus(int i) throws SQLException {
		
		
		String query = "delete from bus_list where id = " + i + "";
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		int rows = st.executeUpdate(query);
		
		System.out.println("Rows Deleted : " + rows);
		}
	
	static ArrayList<Integer> getIDremoveBus(String str1, String str2) throws SQLException {
		
		ArrayList<Integer> ar = new ArrayList();
		
		StringBuilder sb = new StringBuilder("select id from bus_list where");
		
		if(str1 != null && str2 != null) {
			sb.append("From_terminal = ? and To_Terminal = ?");
		}
		
		else if(str1!=null) {
			sb.append("From_terminal = ?");
		}
		else if(str2!=null) {
			sb.append("To_Terminal = ?");
		}
		
		String query = sb.toString();
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement st = con.prepareStatement(query);
		
		
		int parameterindex = 1;
		
		if(str1 != null && str2 != null) {
			st.setString(parameterindex++, str1);
			st.setString(parameterindex++, str2);
		}
		
		else if(str1!=null) {
			st.setString(parameterindex++, str1);
		}
		else if(str2!=null) {
			st.setString(parameterindex++, str2);
		}
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			ar.add(rs.getInt(1));
			getIDremoveBus(rs.getInt(1));
		}
		
		
		return ar;
		
	}	
	
	
	

}

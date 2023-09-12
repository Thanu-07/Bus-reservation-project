package dashboardandfunctionalities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import busResv.DatabaseConnection;

public class GenerateBookNo {
	
	

	private int length = 8;
	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}



	public static void main(String[] args) {
		
		GenerateBookNo gb = new GenerateBookNo();
		 
		
		String bookNo = GenerateBookNo.generateBookNo(gb.length);
		
		
		try {
		
		boolean present = CheckBookNo(bookNo);
		
		while (GenerateBookNo.CheckBookNo(bookNo)) {
			bookNo = generateBookNo(gb.length);
		}
		
		//GenerateBookNo.addBookNo(bookNo);
		// 
		
		}
		
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public static  String generateBookNo(int length) {
		
		 final String values = "0123456789"; // inside method final only permitted not private : illegal modifier
		
		StringBuilder sb = new StringBuilder();
		
		Random rd = new Random();
		
		for(int i=0;i<length;i++) {
		
		int index = rd.nextInt(values.length());
		char c = values.charAt(index);
		sb.append(c);
		
		}
		
		return sb.toString();
	}
	
	
	
	public static boolean CheckBookNo(String bookNo) throws SQLException{
		
		
		String query = "select count(booking_no) from booking_records where booking_no = " + bookNo;
		Connection con = DatabaseConnection.getDatabaseConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		rs.next();
			
		int count = rs.getInt(1);	 
			
		
		return count > 0;
		
		
	}
	
	
	public static void addBookNo(String bookNo) throws SQLException{
		
		
			String query = "insert into booking_records (booking_no) values (" + bookNo + ")";
			Connection con = DatabaseConnection.getDatabaseConnection();
			Statement st = con.createStatement();
			int rows = st.executeUpdate(query);
			
			
		}
		
	}



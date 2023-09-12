package Admindashboardfunctionalities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import busResv.DatabaseConnection;
import dashboardandfunctionalities.End_UserMenu;

public class UpdateBus extends AdminFunction{
	
	int busID;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UpdateBus ub = new UpdateBus();
		
		Scanner s = new Scanner(System.in);
		
		ub.updateBusDetails(s);
		
		try {
			
			ub.updateBus(ub.GetBusID(s));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		

	}
	
	void updateBus(int id) throws SQLException{
		
		String type = this.type;
		Integer capacity = this.capacity;
		String from = this.from;
		String to =this.to;
		
		
	
		
		StringBuilder sb = new StringBuilder("update bus_list set ");
		
		if(type != null) {
			sb.append("Type = ?,");
		}
		if(capacity != null) {
			sb.append("capacity = ?,");
		}
		if(from != null) {
			sb.append("From_terminal = ?,");
		}
		if(to != null) {
			sb.append("To_Terminal = ?");
		}
		
		sb.append("where id = ?;");
		
		Connection con = DatabaseConnection.getDatabaseConnection();
		PreparedStatement pst = con.prepareStatement(sb.toString());
		
		int parameterindex = 1;
		
		if(type != null) {
			pst.setString(parameterindex++, type);
		}
		
		if(capacity != null) {
			pst.setInt(parameterindex++, capacity);
		}
		
		if(from != null) {
			pst.setString(parameterindex++, from);
		}
		
		if(to != null) {
			pst.setString(parameterindex++, to);
		}
		
		pst.setInt(parameterindex, id);
		
		int rows = pst.executeUpdate();
		System.out.println("No.of.rows affected : " + rows);
		
	}

	
	void updateBusDetails(Scanner s){
		
		
	End_UserMenu eu = new End_UserMenu();
		
		boolean found = false;
		
		int adminOpt;
		
		System.out.println(" Update Option : ");
		
		System.out.println("1) Update Bus Type \n 2) Update Bus Capacity \n 3) Update Bus Boarding Place \n 4) Update Droping Place \n 5) Exit Update Bus");
		
		System.out.println("Enter Update Option : ");
		
		
		while(!found) {
			
			
			
			while(true) {
				
				System.out.println("Enter Update Option : ");
			
			try {
			
			 adminOpt = s.nextInt();
			 break;
			
			}
			
			catch(Exception e) {
				s.nextLine();
			}
			}
			
			
			
			
			switch(adminOpt) {
			
			
			case 1 : 
				
				while(true) {
				
				System.out.println("Enter Bus Type : ");
				String Type = s.nextLine();
				if (Type.equals("AC:Sleeper") || Type.equals("AC") || Type.equals("Sleeper")) {
					type = Type;
					break;
				}
				else
					System.out.println("Incorrect Bus type .....");
			
			}
				break;
				
				
				
				
			case 2 :
				
				while(true) {
				
				System.out.println("Enter Bus Capacity : ");
				
				try {
				
					int capc = s.nextInt();
					
					if(capc > 15 && capc < 51) {
						capacity = capc;
						break;
					}
					else
						System.out.println("Bus capacity doesnt below 18 or above 54");
				
				
				}
				catch(Exception e) {
					System.out.println("Not a valid capacity...");
					s.nextLine();
				}
				
				
				}
				
				break;
	
			
			
			case 3 : 
			
			String bp = eu.CheckValidBoardPoint(s);
			
			if(!bp.equals("")) {
				from = bp;
			}
			
			break;
			
			case 4 :
				
				String dp = eu.CheckValidDropPoint(s);
				
				if(!dp.equals("")) {
					to = dp;
				}
			
				break;
				
				
				
				
			case 5 :
				
				found = true;
				break;
				
				default :
					System.out.println("Invalid Update Option ....");
				
			}
			
		}

     }
	
	
	 int  GetBusID(Scanner s) throws SQLException{
			
			ArrayList<Integer> ar = AdminFunction.displayBuses();
			 
			 
		    System.out.println("Available Buses : ");
		    for(Integer i : ar) {
		    	System.out.println(i);
		    	
		    }
			
		    int busno;
			
			while (true) {
				System.out.println("Enter Bus to Update :");

				try {
					busno = s.nextInt();
					if (ar.contains(busno)) {
						 //busNo = busid;
						
						break;
					}
					else
						System.out.println("No bus available....");

				} catch (Exception e) {
					System.out.println(e);
					s.nextLine();
				}
			}
			busID = AdminFunction.getBusID(busno);
			return busID;
		}
	 
	 
	 
	 
	 
	
	
	
	
	
}

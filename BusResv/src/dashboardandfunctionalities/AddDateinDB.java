package dashboardandfunctionalities;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddDateinDB {
	
	
	
	public static void main(String[] args) {
		int year = 2023;
		ArrayList <LocalDate>arr = addDateDB(year);
		
		for(LocalDate ar : arr) {
			System.out.println(ar);
		}
		
	}
	
	public static ArrayList<LocalDate> addDateDB(int year){
		
		ArrayList<LocalDate> ld = new ArrayList<LocalDate>();
		
		LocalDate startdate = LocalDate.of(year, 1, 1);
		LocalDate enddate = LocalDate.of(year, 12, 31);
		
		for(;!startdate.isAfter(enddate);startdate.plusDays(1)) {
			ld.add(startdate);
		}
		return ld;
	}
	

}

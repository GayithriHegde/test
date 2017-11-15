/**
@{author} mallikarjun.guranna
14-Sep-2017
*/
package com.asms.schoolmgmt.helper;

import java.util.Calendar;

public class ValidateAcademicYear {
	/**
	 * @{author} mallikarjun.guranna 14-Sep-2017
	 */

	public static boolean validateAcademicYear(String year) {
		//String year="2017-2018";
		String[] tokenizeYear = year.trim().split("-");
//	System.out.println("tokens are...");
//	System.out.println(tokenizeYear[0]);
//	System.out.println(tokenizeYear[1]);
	
	String fromYear=tokenizeYear[0].substring(2, 4);
	String toYear=tokenizeYear[1].substring(2, 4);
//	System.out.println("from year:"+fromYear);
//	System.out.println("To year:"+toYear);
	
	Calendar now = Calendar.getInstance();   // Gets the current date and time
	int yr = now.get(Calendar.YEAR); 
	//System.out.println("current year: "+yr);
	if((yr == Integer.parseInt(tokenizeYear[0]))  && (Integer.parseInt(toYear)-Integer.parseInt(fromYear)) == 1)
	{
		//System.out.println("Current year valid");
		return true;
	}
	else
	{
		return false;
		//System.out.println("Current year not valid");
	}
		
	}

}

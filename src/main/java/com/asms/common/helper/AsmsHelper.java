package com.asms.common.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class AsmsHelper {

	

	public static final ResourceBundle getMessageFromBundle() {
		Locale currentLocale = new Locale("en", "US");

		ResourceBundle messages = ResourceBundle.getBundle("spring.MessageBundles.ErrorMessages", currentLocale);
		return messages;

	}

	public static boolean validateAcademicYear(String year) {
		// String year="2017-18";
		String[] tokenizeYear = year.trim().split("-");
		// System.out.println("tokens are...");
		// System.out.println(tokenizeYear[0]);
		// System.out.println(tokenizeYear[1]);

		String fromYear = tokenizeYear[0].substring(2, 4);
		String toYear = tokenizeYear[1].substring(0, 2);
		// System.out.println("from year:"+fromYear);
		// System.out.println("To year:"+toYear);

		Calendar now = Calendar.getInstance(); // Gets the current date and time
		int yr = now.get(Calendar.YEAR);
		// System.out.println("current year: "+yr);
		if ((yr == Integer.parseInt(tokenizeYear[0])) && (Integer.parseInt(toYear) - Integer.parseInt(fromYear)) == 1) {
			// System.out.println("Current year valid");
			return true;
		} else {
			// System.out.println("Current year not valid");
			return true;
		}

	}

	public static String getPreviousAcademicYear(String year) {

		String[] tokenizeYear = year.trim().split("-");

		String yearFirstHalf = tokenizeYear[0].substring(0, 2);
		String fromYear = tokenizeYear[0].substring(2, 4);
		String toYear = tokenizeYear[1].substring(0, 2);

		// System.out.println("current year: "+yr);
		int from = (Integer.parseInt(fromYear)) - 1;
		int to = (Integer.parseInt(toYear)) - 1;

		return yearFirstHalf + from + "-" + to;

	}

	public static String getCurrentAcademicYear() {
		Calendar currentDate = Calendar.getInstance();
		String year = null;
		if (currentDate.get(Calendar.MONTH) >= 3)
			year = currentDate.get(Calendar.YEAR) + "-" + (currentDate.get(Calendar.YEAR) + 1);
		else
			year = (currentDate.get(Calendar.YEAR) - 1) + "-" + currentDate.get(Calendar.YEAR);

		return year;
	}

	public static String getTodayDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String generateToken(String email, String domain) {

		long currentTime = System.currentTimeMillis();
		// need this to store in session
		String uuid = UUID.randomUUID().toString().toUpperCase();
		String token = uuid + "|" + email + "|" + domain + "|" + currentTime;
		token = generateHashString(token);

		return token;
	}

	public static boolean checkToken(String token, String sessionToken) {
	
			if (checkPassword(token, sessionToken)) {
				return true;

			} else {
				return false;
			}

		

	}
	
	
	public static String generateHashString(String string){
		return BCrypt.hashpw(string.trim(), BCrypt.gensalt(10));
	}
	
	public static boolean checkPassword(String password, String hashedPassword){
		return BCrypt.checkpw(password, hashedPassword);
	}

}

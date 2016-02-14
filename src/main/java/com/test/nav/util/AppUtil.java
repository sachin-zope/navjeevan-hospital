package com.test.nav.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppUtil {

	public static String getCurrentMonth() {
		return Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
		//return Calendar.getInstance().get(Calendar.MONTH);
	}
	
	public static Integer getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static Date getMTPResetDate(Date operationDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(operationDate);
		int currentYear = cal.get(Calendar.YEAR);
		Calendar resetDate = Calendar.getInstance();
		resetDate.set(currentYear, 0, 1);
		
		return resetDate.getTime();
	}
}

package com.test.nav.util;

import java.util.Calendar;
import java.util.Locale;

public class AppUtil {

	public static String getCurrentMonth() {
		return Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH);
		//return Calendar.getInstance().get(Calendar.MONTH);
	}
	
	public static Integer getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}

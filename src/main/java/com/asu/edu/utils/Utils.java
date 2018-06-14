package com.asu.edu.utils;

import java.util.Calendar;

public class Utils {
	public static String getUniqueNumber(){
	    Calendar lCDateTime = Calendar.getInstance();
	    return lCDateTime.getTimeInMillis()+"";
	}
}

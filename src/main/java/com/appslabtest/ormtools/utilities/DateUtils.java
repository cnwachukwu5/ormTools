package com.appslabtest.ormtools.utilities;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateUtils {
	
	private Calendar currentCalendarInstance;
	String monthName[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	public DateUtils() {
		currentCalendarInstance = Calendar.getInstance();
	}
	
	public Calendar getCurrentCalendarInstance() {
		return currentCalendarInstance;
	}

	public void setCurrentCalendarInstance(Calendar currentCalendarInstance) {
		this.currentCalendarInstance = currentCalendarInstance;
	}

	public String getCurrentMonth() {
		int monthNumber = getCurrentCalendarInstance().get(Calendar.MONTH);
		return monthName[monthNumber];
	}
	
	public String kriMonth() {
		int monthNumber = getCurrentCalendarInstance().get(Calendar.MONTH);
		return monthName[monthNumber - 1];
	}
	
	public String currentQuarter() {
		String currentMonth = getCurrentMonth();
		String currentQuarter = "";
		
		switch (currentMonth) {
		case "January":
		case "February":
		case "March":
			currentQuarter = "Q1";
			break;
		case "April":
		case "May":	
		case "June":
			currentQuarter = "Q2";
			break;
		case "July":
		case "August":	
		case "September":
			currentQuarter = "Q3";
			break;
		case "October":
		case "November":	
		case "December":
			currentQuarter = "Q4";
			break;
		}
		
		return currentQuarter;
	}
	
	public String currentYear() {
		return "" + getCurrentCalendarInstance().get(Calendar.YEAR);
	}
	
	public Timestamp currentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}

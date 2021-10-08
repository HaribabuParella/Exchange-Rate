package com.assessment.exchangeRate.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExchangeRatesUtility {
	public static String getTodayDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now(); 
		return dtf.format(now);
	}
}

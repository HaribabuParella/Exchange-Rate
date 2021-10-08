package com.assessment.exchangeRate.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.assessment.exchangeRate.constants.EchangeRatesConstants;
import com.assessment.exchangeRate.model.ExchangeRate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExchangeRatesUtility {
	
	static Logger logger = LoggerFactory.getLogger(ExchangeRatesUtility.class);
	private ExchangeRatesUtility() {

	}

	public static String getTodayDate() {
		logger.info("ExchangeRatesUtility : getTodayDate");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	public static boolean isDateValid(String dateString, String pattern)
	{
		boolean result = dateString.matches("^\\d+\\-\\d+\\-\\d+");
		if (result) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				if (sdf.format(sdf.parse(dateString)).equals(dateString))
					return true;
			} catch (ParseException pe) {
				logger.error("Parseing exception", pe);
			}
		}
		return false;
	}

	public static List<ExchangeRate> filterExchangeRateListData(List<ExchangeRate> exchangeRateList) {
		logger.info("ExchangeRatesUtility : filterExchangeRateListData");
		List<ExchangeRate> exchangeRateList1 = exchangeRateList.stream()
				.filter(a -> a.getDate().substring(8, 10).contains("01")).collect(Collectors.toList());
		List<String> wordsList = Arrays.asList("GBP", "USD", "HKD ");

		return exchangeRateList1.stream().filter(a -> a.getRates().entrySet().containsAll(wordsList))
				.collect(Collectors.toList());
	}

	public static List<ExchangeRate> getExchangeDataFromAPI(String accessKey) throws JsonProcessingException {
		logger.info("getExchangeDatFromAPI");
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		String json = rt.getForObject(EchangeRatesConstants.URL2+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		ExchangeRate exchangeRate = mapper.reader().forType(ExchangeRate.class).readValue(json);
		exchangeRateList.add(exchangeRate);
	
		  String json1 = rt.getForObject(EchangeRatesConstants.URL3+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate1 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json1);
		  exchangeRateList.add(exchangeRate1);
		  
		  String json2 = rt.getForObject(EchangeRatesConstants.URL4+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate2 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json2);
		  exchangeRateList.add(exchangeRate2);
		  
		  String json3 = rt.getForObject(EchangeRatesConstants.URL5+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate3 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json3);
		  exchangeRateList.add(exchangeRate3);
		 
		  String json4 = rt.getForObject(EchangeRatesConstants.URL6+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate4 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json4);
		  exchangeRateList.add(exchangeRate4);
		  
		  
		  String json5 = rt.getForObject(EchangeRatesConstants.URL7+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate5 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json5);
		  
		  String json6 = rt.getForObject(EchangeRatesConstants.URL8+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate6 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json6);
		  
		  String json7 = rt.getForObject(EchangeRatesConstants.URL9+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate7 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json7);
		 
		  String json8 = rt.getForObject(EchangeRatesConstants.URL10+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate8 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json8);
		  
		  String json9 = rt.getForObject(EchangeRatesConstants.URL11+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate9 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json9);
		  
		  String json10 = rt.getForObject(EchangeRatesConstants.URL12+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate10 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json10);
		  
		  String json11 = rt.getForObject(EchangeRatesConstants.URL+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate11 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json11);
		
		 
		exchangeRateList.add(exchangeRate5); exchangeRateList.add(exchangeRate6);
		exchangeRateList.add(exchangeRate7); exchangeRateList.add(exchangeRate8);
		exchangeRateList.add(exchangeRate9); exchangeRateList.add(exchangeRate10);
		exchangeRateList.add(exchangeRate11);
		return exchangeRateList;
	}

	public static List<ExchangeRate> getExchangeRatesDataFromAPI(String accessKey) throws JsonMappingException, JsonProcessingException {
		logger.info("getExchangeDatFromAPI");
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		String json = rt.getForObject(EchangeRatesConstants.URL2+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		ExchangeRate exchangeRate = mapper.reader().forType(ExchangeRate.class).readValue(json);
		exchangeRateList.add(exchangeRate);
	
		  String json1 = rt.getForObject(EchangeRatesConstants.URL3+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate1 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json1);
		  exchangeRateList.add(exchangeRate1);
		  
		  String json2 = rt.getForObject(EchangeRatesConstants.URL4+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate2 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json2);
		  exchangeRateList.add(exchangeRate2);
		  
		  String json3 = rt.getForObject(EchangeRatesConstants.URL5+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate3 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json3);
		  exchangeRateList.add(exchangeRate3);
		 
		  String json4 = rt.getForObject(EchangeRatesConstants.URL6+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate4 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json4);
		  exchangeRateList.add(exchangeRate4);
		  
		  
		  String json5 = rt.getForObject(EchangeRatesConstants.URL7+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate5 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json5);
		  
		  String json6 = rt.getForObject(EchangeRatesConstants.URL8+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE+EchangeRatesConstants.CURRENCY_SYMBOL, String.class);
		  ExchangeRate exchangeRate6 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json6);
		  
		  String json7 = rt.getForObject(EchangeRatesConstants.URL9+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate7 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json7);
		 
		  String json8 = rt.getForObject(EchangeRatesConstants.URL10+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate8 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json8);
		  
		  String json9 = rt.getForObject(EchangeRatesConstants.URL11+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate9 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json9);
		  
		  String json10 = rt.getForObject(EchangeRatesConstants.URL12+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate10 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json10);
		  
		  String json11 = rt.getForObject(EchangeRatesConstants.URL+EchangeRatesConstants.ACCESS_KEY+accessKey+
				  EchangeRatesConstants.BASE, String.class);
		  ExchangeRate exchangeRate11 =
		  mapper.reader().forType(ExchangeRate.class).readValue(json11);
		
		 
		exchangeRateList.add(exchangeRate5); exchangeRateList.add(exchangeRate6);
		exchangeRateList.add(exchangeRate7); exchangeRateList.add(exchangeRate8);
		exchangeRateList.add(exchangeRate9); exchangeRateList.add(exchangeRate10);
		exchangeRateList.add(exchangeRate11);
		return exchangeRateList;
	}
}

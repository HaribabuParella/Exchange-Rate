package com.assessment.exchangeRate.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.exchangeRate.constants.EchangeRatesConstants;
import com.assessment.exchangeRate.model.ExchangeRate;
import com.assessment.exchangeRate.repository.ExchangeRatesRepository;
import com.assessment.exchangeRate.utility.ExchangeRatesUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeRateService {
	Logger logger = LoggerFactory.getLogger(ExchangeRateService.class);

	@Autowired
	ExchangeRatesRepository exchangeRatesRepository;

	public String getExchangeRates(String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRates");
		List<ExchangeRate> exchangeRateData = ExchangeRatesUtility.getExchangeDataFromAPI(accessKey);
		if(!exchangeRateData.isEmpty()) {
			logger.info("getExchangeRates : Inserting data into table");
			exchangeRatesRepository.saveAll(exchangeRateData);
			return "Data inserted successfully !!";
		}
		return "Data not recived from Echange API !!";
	}
	
	public String getExchangeRatesByDate(String accessKey, String date) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRatesByDate");
		boolean result = ExchangeRatesUtility.isDateValid(date, EchangeRatesConstants.DATE_FORMATE);
		if (result) {
			String url = EchangeRatesConstants.BASE_URL + date + EchangeRatesConstants.ACCESS_KEY
					+ accessKey + EchangeRatesConstants.BASE
					+ EchangeRatesConstants.SYMBOL;
			logger.info("getExchangeRatesByDate : " + url);
			RestTemplate rt = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			String json = rt.getForObject(url, String.class);
			ExchangeRate exchangeRate = mapper.reader().forType(ExchangeRate.class).readValue(json);
			if (exchangeRate != null && !exchangeRate.getDate().isEmpty()) {
				logger.info("getExchangeRatesByDate : Inserting data into table");
				exchangeRatesRepository.save(exchangeRate);
				return "Data inserted successfully !!";
			}
			else {
				return "Data not recived from Echange API !!";
			}
		}
		return "Invalid Dateformate from user !!";
	}
	
	public String getExchangeRatesData(String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRatesData");
		List<ExchangeRate> exchangeRateData = ExchangeRatesUtility.getExchangeRatesDataFromAPI(accessKey);
		if(!exchangeRateData.isEmpty()) {
			logger.info("getExchangeRates : Inserting data into table");
			exchangeRatesRepository.saveAll(exchangeRateData);
			return "Data inserted successfully !!";
		}
		return "Data not recived from Echange API !!";
	}
	
	public Object getExchangeRatesInfoByDate(String date) {
		logger.info("ExchangeRateService : getExchangeRatesInfoByDate");
		Object resultObject = null;
		boolean result = ExchangeRatesUtility.isDateValid(date, EchangeRatesConstants.DATE_FORMATE);
		if (result) {
			ExchangeRate exchangeRate = exchangeRatesRepository.findByDate(date);
			if (exchangeRate !=null && !exchangeRate.getDate().isEmpty()) {
				resultObject = exchangeRate;
			} else {
				String error = "No Exchange Rate data found for this Date !!";
				resultObject = error;
			}
		} else {
			String error = "Invalid Dateformate from user !!";
			resultObject = error;
		}
		return resultObject;
	}

	public List<Object> getExchangeRatesInBwtDates(String fromDate, String toDate) {
		logger.info("ExchangeRateService : getExchangeRatesInfoByDate");
		boolean result = ExchangeRatesUtility.isDateValid(fromDate, EchangeRatesConstants.DATE_FORMATE);
		boolean result1 = ExchangeRatesUtility.isDateValid(toDate, EchangeRatesConstants.DATE_FORMATE);
		List<Object> exchangeRatesList = new ArrayList<>();
		if(result&&result1){
			exchangeRatesList = exchangeRatesRepository.findAllByDateBetween(fromDate, toDate);
			String todayDate = ExchangeRatesUtility.getTodayDate();
			logger.info(" getExchangeRatesInBwtDates todatDate"+todayDate);
			ExchangeRate exchangeRate= exchangeRatesRepository.findByDate(todayDate);
			if(!exchangeRatesList.isEmpty()) {
				if(exchangeRate!= null && !exchangeRate.getDate().isEmpty()) {
					exchangeRatesList.add(exchangeRate);
				}
				else {
					exchangeRatesList.add("No Exchange Rate data found for today Date !!");
				}
			}else {
				if(exchangeRate!= null && !exchangeRate.getDate().isEmpty()) {
					exchangeRatesList.add(exchangeRate);
					exchangeRatesList.add("No Exchange Rate data found for in b/w Dates !!");
				}
				else {
					exchangeRatesList.add("No Exchange Rate data found in b/w Dates and Today date !!");
				}
			}
			
		}else {
			String error = "Invalid Dateformate from user !!";
			exchangeRatesList.add(error);
		}
		return exchangeRatesList;
	}

}

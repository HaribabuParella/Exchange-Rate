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

	public String getExchangeRates() throws JsonProcessingException {
		List<ExchangeRate> exchangeRateData = ExchangeRatesUtility.getExchangeDatFromAPI();
		if(!exchangeRateData.isEmpty()) {
			exchangeRatesRepository.saveAll(exchangeRateData);
			return "Data inserted successfully !!";
		}
		return "Data not recived from Echange API !!";
	}
	
	public String getExchangeRatesByDate(String date) throws JsonProcessingException {
		String url = EchangeRatesConstants.BASE_URL+date+EchangeRatesConstants.ACCESS_KEY+EchangeRatesConstants.ACCESS_KEY_VALUE+EchangeRatesConstants.BASE+EchangeRatesConstants.SYMBOL;
		//String url = "http://api.exchangeratesapi.io/v1/2020-12-01?access_key=8bcc7821dd1cfbdf4cbae85e11ff220a&base=EUR&symbols=GBP,USD,HKD";
		logger.info("getExchangeRatesByDate : "+url);
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		String json = rt.getForObject(url, String.class);
		ExchangeRate exchangeRate = mapper.reader().forType(ExchangeRate.class).readValue(json);
		exchangeRatesRepository.save(exchangeRate);
		return "Data inserted successfully !!";
	}
	
	public List<ExchangeRate> getExchangeRatesData() {
		List<ExchangeRate> exchangeRatesList = new ArrayList<>();  
		exchangeRatesRepository.findAll().forEach(exchangeRatesList::add);  
		return exchangeRatesList;
	}
	
	public ExchangeRate getExchangeRatesInfoByDate(String date) {
		return exchangeRatesRepository.findByDate(date);
	}

	public List<ExchangeRate> getExchangeRatesInBwtDates(String fromDate, String toDate) {
		List<ExchangeRate> exchangeRatesList = exchangeRatesRepository.findAllByDateBetween(fromDate, toDate);
		String todayDate = ExchangeRatesUtility.getTodayDate();
		ExchangeRate exchangeRate= exchangeRatesRepository.findByDate(todayDate);
		exchangeRatesList.add(exchangeRate);
		return exchangeRatesList;
	}

}

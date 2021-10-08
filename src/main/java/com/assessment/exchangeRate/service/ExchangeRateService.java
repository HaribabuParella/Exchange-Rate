package com.assessment.exchangeRate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.exchangeRate.model.ExchangeRate;
import com.assessment.exchangeRate.repository.ExchangeRatesRepository;
import com.assessment.exchangeRate.utility.ExchangeRatesUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeRateService {

	@Autowired
	ExchangeRatesRepository exchangeRatesRepository;

	public String getExchangeRates() throws JsonProcessingException {
		String url = "http://api.exchangeratesapi.io/v1/latest?access_key=8bcc7821dd1cfbdf4cbae85e11ff220a";
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		String json = rt.getForObject(url, String.class);
		//List<ExchangeRate> exchangeRateList = mapper.reader().forType(List.class).readValue(json);
		List<ExchangeRate> exchangeRateList = mapper.readValue(json, new TypeReference<List<ExchangeRate>>(){});
		exchangeRatesRepository.saveAll(exchangeRateList);
		return "Data inserted successfully !!";
	}
	
	public List<ExchangeRate> getExchangeRatesData() {
		List<ExchangeRate> exchangeRatesList = new ArrayList<>();  
		exchangeRatesRepository.findAll().forEach(exchangeRatesList::add);  
		return exchangeRatesList;
	}
	
	public ExchangeRate getExchangeRatesInfoByDate(String date) {
		System.out.println("getExchangeRatesInfoByDate -- Service layer");
		return exchangeRatesRepository.findByDate(date);
	}

	public String getExchangeRatesByDate(String date) throws JsonProcessingException {
		String url = "http://api.exchangeratesapi.io/v1/latest?access_key=8bcc7821dd1cfbdf4cbae85e11ff220a&base=EUR&symbols=GBP";
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		String json = rt.getForObject(url, String.class);
		ExchangeRate exchangeRate = mapper.reader().forType(ExchangeRate.class).readValue(json);
		exchangeRatesRepository.save(exchangeRate);
		return "Data inserted successfully !!";
	}

	public List<ExchangeRate> getExchangeRatesInBwtDates(String fromDate, String toDate) {
		List<ExchangeRate> exchangeRatesList = exchangeRatesRepository.findAllByDateBetween(fromDate, toDate);
		String todayDate = ExchangeRatesUtility.getTodayDate();
		ExchangeRate exchangeRate= exchangeRatesRepository.findByDate(todayDate);
		exchangeRatesList.add(exchangeRate);
		return exchangeRatesList;
	}

}

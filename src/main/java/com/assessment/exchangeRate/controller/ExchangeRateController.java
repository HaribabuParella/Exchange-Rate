package com.assessment.exchangeRate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.assessment.exchangeRate.model.ExchangeRate;
import com.assessment.exchangeRate.service.ExchangeRateService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ExchangeRateController {
	
	@Autowired
	ExchangeRateService exchangeRateService;
	
	@GetMapping(value="/getExchangRates")
	private String getExchangeRates() throws JsonProcessingException {
		return exchangeRateService.getExchangeRates();
	}
	
	@GetMapping(value="/getExchangeRatesByDate/{date}")
	private String getExchangeRatesByDate(@PathVariable("date") String date) throws JsonProcessingException {
		return exchangeRateService.getExchangeRatesByDate(date);
	}
	
	@GetMapping(value="/getExchangeRatesData")
	private List<ExchangeRate> getExchangeRatesData() {
		return exchangeRateService.getExchangeRatesData();
	}
	
	@GetMapping(value="/getExchangeRatesInfoByDate/{date}")
	private ExchangeRate getExchangeRatesInfoByDate(@PathVariable("date") String date) {
		return exchangeRateService.getExchangeRatesInfoByDate(date);//2021-10-08
	}
	
	@GetMapping(value="/getExchangeRatesInBwtDates/{fromDate}/{toDate}")
	private List<ExchangeRate> getExchangeRatesInBwtDates(@PathVariable("fromDate") String fromDate,@PathVariable("toDate") String toDate) {
		return exchangeRateService.getExchangeRatesInBwtDates(fromDate,toDate);//2021-10-08
	}
	
}

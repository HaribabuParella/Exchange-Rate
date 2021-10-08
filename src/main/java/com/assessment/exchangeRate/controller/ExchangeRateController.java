package com.assessment.exchangeRate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.assessment.exchangeRate.model.ExchangeRate;
import com.assessment.exchangeRate.service.ExchangeRateService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ExchangeRateController {
	
	Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);

	@Autowired
	ExchangeRateService exchangeRateService;
	
	@GetMapping(value="/getExchangRates")
	private String getExchangeRates() throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRates Entry");
		return exchangeRateService.getExchangeRates();
	}
	
	@GetMapping(value="/getExchangeRatesByDate/{date}")
	private String getExchangeRatesByDate(@PathVariable("date") String date) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRatesByDate Entry");
		logger.info("getExchangeRatesByDate input date: "+date);
		return exchangeRateService.getExchangeRatesByDate(date);
	}
	
	@GetMapping(value="/getExchangeRatesData")
	private List<ExchangeRate> getExchangeRatesData() {
		logger.info("ExchangeRateController : getExchangeRatesData Entry");
		return exchangeRateService.getExchangeRatesData();
	}
	
	@GetMapping(value="/getExchangeRatesInfoByDate/{date}")
	private ExchangeRate getExchangeRatesInfoByDate(@PathVariable("date") String date) {
		logger.info("ExchangeRateController : getExchangeRatesInfoByDate Entry");
		logger.info("getExchangeRatesInfoByDate input date: "+date);
		return exchangeRateService.getExchangeRatesInfoByDate(date);//2021-10-08
	}
	
	@GetMapping(value="/getExchangeRatesInBwtDates/{fromDate}/{toDate}")
	private List<ExchangeRate> getExchangeRatesInBwtDates(@PathVariable("fromDate") String fromDate,@PathVariable("toDate") String toDate) {
		logger.info("ExchangeRateController : getExchangeRatesInBwtDates Entry");
		logger.info("getExchangeRatesInBwtDates Input fromdate "+fromDate+" todate "+toDate);
		return exchangeRateService.getExchangeRatesInBwtDates(fromDate,toDate);//2021-10-08
	}
	
}

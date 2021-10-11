package com.assessment.exchangeRate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.exchangeRate.service.ExchangeRateService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ExchangeRateController {
	
	Logger logger = LoggerFactory.getLogger(ExchangeRateController.class);

	@Autowired
	ExchangeRateService exchangeRateService;
	
	@GetMapping(value="/get_Exchang_Rates/{accessKey}")
	private String getExchangeRates(@PathVariable("accessKey") String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRates Entry");
		return exchangeRateService.getExchangeRates(accessKey);
	}
	
	@GetMapping(value="/getExchangeRatesByDate/{accessKey}/{date}")
	private String getExchangeRatesByDate(@PathVariable("accessKey") String accessKey,@PathVariable("date") String date) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRatesByDate Entry ");
		return exchangeRateService.getExchangeRatesByDate(accessKey,date);
	}
	
	@GetMapping(value="/getExchangeRatesData/{accessKey}")
	private String getExchangeRatesData(@PathVariable("accessKey") String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateController : getExchangeRatesData Entry");
		return exchangeRateService.getExchangeRatesData(accessKey);
	}
	
	@GetMapping(value="/getExchangeRatesInfoByDate/{date}")
	private Object getExchangeRatesInfoByDate(@PathVariable("date") String date) {
		logger.info("ExchangeRateController : getExchangeRatesInfoByDate Entry ");
		return exchangeRateService.getExchangeRatesInfoByDate(date);//2021-10-08
	}
	
	@GetMapping(value="/getExchangeRatesInBwtDates/{fromDate}/{toDate}")
	private List<Object> getExchangeRatesInBwtDates(@PathVariable("fromDate") String fromDate,@PathVariable("toDate") String toDate) {
		logger.info("ExchangeRateController : getExchangeRatesInBwtDates Entry");
		return exchangeRateService.getExchangeRatesInBwtDates(fromDate,toDate);//2021-10-08
	}
	
}

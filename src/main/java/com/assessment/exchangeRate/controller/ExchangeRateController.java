package com.assessment.exchangeRate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.exchangeRate.model.ExchangeRate;
import com.assessment.exchangeRate.service.ExchangeRateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ExchangeRateController {
	
	@Autowired
	ExchangeRateService exchangeRateService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@GetMapping(value="/getExchangrRates")
	private ExchangeRate getExchangeRates() throws JsonMappingException, JsonProcessingException {
		/*
		 * String url =
		 * "http://api.exchangeratesapi.io/v1/latest?access_key=8bcc7821dd1cfbdf4cbae85e11ff220a";
		 * RestTemplate rt = new RestTemplate(); ObjectMapper mapper = new
		 * ObjectMapper(); String json = rt.getForObject(url, String.class);
		 * ExchangeRate exchangeRate = mapper.reader()
		 * .forType(ExchangeRate.class).readValue(json);
		 */
		return exchangeRateService.getExchangeRates();
	}
}

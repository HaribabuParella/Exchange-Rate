package com.assessment.exchangeRate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.assessment.exchangeRate.model.ExchangeRate;
import com.assessment.exchangeRate.repository.ExchangeRatesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeRateService {
	
	@Autowired  
	ExchangeRatesRepository exchangeRatesRepository; 
	
   public ExchangeRate getExchangeRates() throws JsonMappingException, JsonProcessingException {
		String url = "http://api.exchangeratesapi.io/v1/latest?access_key=8bcc7821dd1cfbdf4cbae85e11ff220a";
		RestTemplate rt = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		String json = rt.getForObject(url, String.class);
		ExchangeRate exchangeRate = mapper.reader()
			      .forType(ExchangeRate.class).readValue(json);
		exchangeRatesRepository.save(exchangeRate);
		return exchangeRate;
	}

}

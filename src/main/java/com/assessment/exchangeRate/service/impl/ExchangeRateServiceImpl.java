package com.assessment.exchangeRate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.assessment.exchangeRate.Exceptions.CustomException;
import com.assessment.exchangeRate.Exceptions.UnauthorizedException;
import com.assessment.exchangeRate.constants.ExchangeRatesConstants;
import com.assessment.exchangeRate.model.ExchangeRate;
import com.assessment.exchangeRate.repository.ExchangeRatesRepository;
import com.assessment.exchangeRate.service.ExchangeRateService;
import com.assessment.exchangeRate.utility.ExchangeRatesUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {
	Logger logger = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

	@Autowired
	ExchangeRatesRepository exchangeRatesRepository;

	public String getExchangeRates(String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRates");
		List<ExchangeRate> exchangeRateData = ExchangeRatesUtility.getExchangeDataFromAPI(accessKey);
		if(!exchangeRateData.isEmpty()) {
			logger.info("getExchangeRates : Inserting data into table");
			exchangeRatesRepository.saveAll(exchangeRateData);
			return ExchangeRatesConstants.SAVE_MSG;
		}
		return ExchangeRatesConstants.N0_DATA_FOUND;
	}
	
	public String getExchangeRatesByDate(String accessKey, String date) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRatesByDate");
		boolean result = ExchangeRatesUtility.isDateValid(date, ExchangeRatesConstants.DATE_FORMATE);
		try {
		if (result) {
			String url = ExchangeRatesConstants.BASE_URL + date + ExchangeRatesConstants.ACCESS_KEY
					+ accessKey + ExchangeRatesConstants.BASE
					+ ExchangeRatesConstants.SYMBOL;
			RestTemplate rt = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();
			String json = rt.getForObject(url, String.class);
			ExchangeRate exchangeRate = mapper.reader().forType(ExchangeRate.class).readValue(json);
			if (exchangeRate != null && !exchangeRate.getDate().isEmpty()) {
				logger.info("getExchangeRatesByDate : Inserting data into table");
				exchangeRatesRepository.save(exchangeRate);
				return ExchangeRatesConstants.SAVE_MSG;
			}
			else {
				return ExchangeRatesConstants.N0_DATA_FOUND;
			}
		}
		}catch(JsonProcessingException jpe) {
			throw new CustomException(ExchangeRatesConstants.UNKNOW_RESPONSE);
		}catch(HttpClientErrorException hce) {
			throw new UnauthorizedException(ExchangeRatesConstants.INVALID_ACCESS_KEY);
		}catch(ResourceAccessException rae) {
			throw new CustomException(ExchangeRatesConstants.SERVER_ERROR);
		}catch(Exception e) {
			throw new CustomException(ExchangeRatesConstants.BASE_ERROR);
		}
		return ExchangeRatesConstants.INVALID_INPUT;
	}
	
	public String getExchangeRatesData(String accessKey) throws JsonProcessingException {
		logger.info("ExchangeRateService : getExchangeRatesData");
		List<ExchangeRate> exchangeRateData = ExchangeRatesUtility.getExchangeRatesDataFromAPI(accessKey);
		if(!exchangeRateData.isEmpty()) {
			logger.info("getExchangeRates : Inserting data into table");
			exchangeRatesRepository.saveAll(exchangeRateData);
			return ExchangeRatesConstants.SAVE_MSG;
		}
		return ExchangeRatesConstants.N0_DATA_FOUND;
	}
	
	public Object getExchangeRatesInfoByDate(String date) {
		logger.info("ExchangeRateService : getExchangeRatesInfoByDate");
		Object resultObject = null;
		boolean result = ExchangeRatesUtility.isDateValid(date, ExchangeRatesConstants.DATE_FORMATE);
		if (result) {
			ExchangeRate exchangeRate = exchangeRatesRepository.findByDate(date);
			if (exchangeRate !=null && !exchangeRate.getDate().isEmpty()) {
				resultObject = exchangeRate;
			} else {
				resultObject = ExchangeRatesConstants.N0_DATA_FOUND_DATE;
			}
		} else {
			resultObject = ExchangeRatesConstants.INVALID_INPUT;
		}
		return resultObject;
	}

	public List<Object> getExchangeRatesInBwtDates(String fromDate, String toDate) {
		logger.info("ExchangeRateService : getExchangeRatesInfoByDate");
		boolean result = ExchangeRatesUtility.isDateValid(fromDate, ExchangeRatesConstants.DATE_FORMATE);
		boolean result1 = ExchangeRatesUtility.isDateValid(toDate, ExchangeRatesConstants.DATE_FORMATE);
		List<Object> exchangeRatesList = new ArrayList<>();
		if(result&&result1){
			exchangeRatesList = exchangeRatesRepository.findAllByDateBetween(fromDate, toDate);
			String todayDate = ExchangeRatesUtility.getTodayDate();
			ExchangeRate exchangeRate= exchangeRatesRepository.findByDate(todayDate);
			if(!exchangeRatesList.isEmpty()) {
				if(exchangeRate!= null && !exchangeRate.getDate().isEmpty()) {
					exchangeRatesList.add(exchangeRate);
				}
				else {
					exchangeRatesList.add(ExchangeRatesConstants.N0_DATA_FOUND_DATE_TODAY);
				}
			}else {
				if(exchangeRate!= null && !exchangeRate.getDate().isEmpty()) {
					exchangeRatesList.add(exchangeRate);
					exchangeRatesList.add(ExchangeRatesConstants.N0_DATA_FOUND_BTW_DATES);
				}
				else {
					exchangeRatesList.add(ExchangeRatesConstants.N0_DATA_FOUND_BTW_DATES_TODAY_DATE);
				}
			}
			
		}else {
			exchangeRatesList.add(ExchangeRatesConstants.INVALID_INPUT);
		}
		return exchangeRatesList;
	}

}

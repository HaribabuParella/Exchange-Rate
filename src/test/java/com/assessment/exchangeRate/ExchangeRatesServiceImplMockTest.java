package com.assessment.exchangeRate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.assessment.exchangeRate.constants.ExchangeRatesConstants;
import com.assessment.exchangeRate.model.ExchangeRate;
import com.assessment.exchangeRate.repository.ExchangeRatesRepository;
import com.assessment.exchangeRate.service.impl.ExchangeRateServiceImpl;
import com.assessment.exchangeRate.utility.ExchangeRatesUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class ExchangeRatesServiceImplMockTest {

	@InjectMocks
	private ExchangeRateServiceImpl exchangeRateService;
	
	@MockBean
	private ExchangeRatesRepository exchangeRatesRepository;
	
	@MockBean
	private ExchangeRatesUtility exchangeRatesUtility;
	@MockBean
	private RestTemplate restTemplate;
	@Mock
	private ObjectMapper objectMapper;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void getExchangeRates() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		System.out.println("Test case start!!");
		exchangeRate.setRates(rates);
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		exchangeRateList.add(exchangeRate);
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.getExchangeDataFromAPI(ExchangeRatesConstants.ACCESS_KEY_VALUE))
					.thenReturn(exchangeRateList);
			Mockito.when(exchangeRatesRepository.saveAll(exchangeRateList)).thenReturn(exchangeRateList);
			String result = exchangeRateService.getExchangeRates(ExchangeRatesConstants.ACCESS_KEY_VALUE);
			assertEquals(ExchangeRatesConstants.SAVE_MSG, result);
		}
	}
	
	@Test
	void getExchangeRatesWithoutData() throws Exception {
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.getExchangeDataFromAPI(ExchangeRatesConstants.ACCESS_KEY_VALUE))
					.thenReturn(exchangeRateList);
			Mockito.when(exchangeRatesRepository.saveAll(exchangeRateList)).thenReturn(exchangeRateList);
			String result = exchangeRateService.getExchangeRates(ExchangeRatesConstants.ACCESS_KEY_VALUE);
			assertEquals(ExchangeRatesConstants.N0_DATA_FOUND, result);
		}
	}
	
	@Test
	void getExchangeRatesData() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		Map<String, Double> rates = new HashMap<>();
		rates.put("GBP", 0.853913);
		exchangeRate.setRates(rates);
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		exchangeRateList.add(exchangeRate);
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.getExchangeRatesDataFromAPI(ExchangeRatesConstants.ACCESS_KEY_VALUE))
					.thenReturn(exchangeRateList);
			Mockito.when(exchangeRatesRepository.saveAll(exchangeRateList)).thenReturn(exchangeRateList);
			String result = exchangeRateService.getExchangeRates(ExchangeRatesConstants.ACCESS_KEY_VALUE);
			assertEquals(ExchangeRatesConstants.SAVE_MSG, result);
		}
	}
	
	@Test
	void getExchangeRatesDataWithoutData() throws Exception {
		List<ExchangeRate> exchangeRateList = new ArrayList<>();
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.getExchangeRatesDataFromAPI(ExchangeRatesConstants.ACCESS_KEY_VALUE))
					.thenReturn(exchangeRateList);
			Mockito.when(exchangeRatesRepository.saveAll(exchangeRateList)).thenReturn(exchangeRateList);
			String result = exchangeRateService.getExchangeRates(ExchangeRatesConstants.ACCESS_KEY_VALUE);
			assertEquals(ExchangeRatesConstants.N0_DATA_FOUND, result);
		}
	}
	
	@Test
	void getExchangeRatesByDate() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		String reponse = "[{\"success\":\"true\",\"timestamp\":\"564687465\",\"historical\":\"true\",\"base\":\"EUR\",\"date\":\"2021-10-01\",\"rates\":{\"GBP\":0.853913}}]";
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.isDateValid(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
			Mockito.when(restTemplate.getForObject(Mockito.anyString(), eq(String.class))).thenReturn(reponse);
			Mockito.when(objectMapper.readValue(Mockito.anyString(), eq(ExchangeRate.class))).thenReturn(exchangeRate);
			Mockito.when(exchangeRatesRepository.save(exchangeRate)).thenReturn(exchangeRate);
			String result = exchangeRateService.getExchangeRatesByDate(ExchangeRatesConstants.ACCESS_KEY_VALUE, "2021-10-01");
			assertEquals(ExchangeRatesConstants.SAVE_MSG, result);
		}
		
	}
	
	@Test
	void getExchangeRatesInfoByDate() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.isDateValid(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
			when(exchangeRatesRepository.findByDate(anyString())).thenReturn(exchangeRate);
			Object result = exchangeRateService.getExchangeRatesInfoByDate("2021-10-01");
			assertNotNull(result);
		}
	}
	
	@Test
	void getExchangeRatesInfoByDateWithoutData() throws Exception {
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.isDateValid(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
			when(exchangeRatesRepository.findByDate(anyString())).thenReturn(null);
			Object result = exchangeRateService.getExchangeRatesInfoByDate("2021-10-01");
			assertNotNull(result);
		}
	}
	
	@Test
	void getExchangeRatesInBwtDates() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.setBase("EUR");
		exchangeRate.setDate("2021-10-01");
		exchangeRate.setHistorical("true");
		exchangeRate.setSuccess("true");
		exchangeRate.setTimestamp("564687465");
		List<Object> exchangeRateList = new ArrayList<>();
		exchangeRateList.add(exchangeRate);
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.isDateValid(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
			Mockito.when(exchangeRatesRepository.findAllByDateBetween(anyString(),anyString())).thenReturn(exchangeRateList);
			Mockito.when(exchangeRatesRepository.findByDate(anyString())).thenReturn(exchangeRate);
			List<Object> result = exchangeRateService.getExchangeRatesInBwtDates("2021-01-01","2021-10-01");
			assertNotNull(result);
		}
		
	}
	
	@Test
	void getExchangeRatesInBwtDatesWithoutData() throws Exception {
		ExchangeRate exchangeRate = new ExchangeRate();
		List<Object> exchangeRateList = new ArrayList<>();
		try (MockedStatic<ExchangeRatesUtility> mocked = mockStatic(ExchangeRatesUtility.class)) {
			mocked.when(() -> ExchangeRatesUtility.isDateValid(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
			Mockito.when(exchangeRatesRepository.findAllByDateBetween(anyString(),anyString())).thenReturn(exchangeRateList);
			Mockito.when(exchangeRatesRepository.findByDate(anyString())).thenReturn(exchangeRate);
			List<Object> result =exchangeRateService.getExchangeRatesInBwtDates("2021-01-01","2021-10-01");
			assertNotNull(result);
		}
		
	}
	
}

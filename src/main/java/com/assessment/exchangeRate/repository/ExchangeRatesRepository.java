package com.assessment.exchangeRate.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.assessment.exchangeRate.model.ExchangeRate;

@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRate, Integer>{
	 //@Query(value = "select * from EXCHANGE_RATES er where er.date(:date)")
	 ExchangeRate findByDate(@Param("date") String date);
	 
	 List<ExchangeRate> findAllByDateBetween(String fromDate,String toDate);
}

package com.assessment.exchangeRate.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.assessment.exchangeRate.model.ExchangeRate;

@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRate, Integer>{
	 ExchangeRate findByDate(@Param("date") String date);
	 
	 List<Object> findAllByDateBetween(String fromDate,String toDate);
}

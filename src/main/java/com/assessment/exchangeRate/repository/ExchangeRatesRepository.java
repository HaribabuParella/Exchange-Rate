package com.assessment.exchangeRate.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.exchangeRate.model.ExchangeRate;

/**
 * From this call will call DB and do DB operations 
 * @author Haribabu Parella
 *
 */
@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRate, Integer>{
	 /**
	 * @param date
	 * @return
	 */
	ExchangeRate findByDate(@Param("date") String date);
	 
	 /**
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	List<Object> findAllByDateBetween(String fromDate,String toDate);
}

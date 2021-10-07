package com.assessment.exchangeRate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assessment.exchangeRate.model.ExchangeRate;

@Repository
public interface ExchangeRatesRepository extends CrudRepository<ExchangeRate, Integer>{

}

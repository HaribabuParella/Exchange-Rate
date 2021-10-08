package com.assessment.exchangeRate.model;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "EXCHANGE_RATES", schema = "PUBLIC")
public class ExchangeRate {
	
	//@Id
	//@Column
	//@GeneratedValue 
	//private int id;
	
	@Column
	private String success;
	@Column
	private String timestamp;
	@Id
	@Column
	private String base;
	@Column
	private String date;
	@ElementCollection
	@MapKeyColumn
	@Column
	@CollectionTable
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private Map<String,Long> rates;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Map<String, Long> getRates() {
		return rates;
	}
	public void setRates(Map<String, Long> rates) {
		this.rates = rates;
	}
	@Override
	public String toString() {
		return "ExchangeRate [success=" + success + ", timestamp=" + timestamp + ", base=" + base + ", date=" + date
				+ ", rates=" + rates + "]";
	}
	
}

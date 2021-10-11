package com.assessment.exchangeRate.Exceptions;

/**
 * This is customException class , when we got custom exception it
 * will raise exception
 * 
 * @author Haribabu Parella
 *
 */
@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	public CustomException(String message) {
		super(message);
	}
}

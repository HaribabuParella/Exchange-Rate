package com.assessment.exchangeRate.Exceptions;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
        super(message);
    }
}

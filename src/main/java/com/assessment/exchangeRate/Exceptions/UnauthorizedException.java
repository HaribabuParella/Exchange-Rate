package com.assessment.exchangeRate.Exceptions;

/**
 * @author Haribabu Parella
 *
 */
@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
        super(message);
    }
}

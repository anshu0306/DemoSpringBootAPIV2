package com.demo.hoteBookingException;

import org.springframework.http.HttpStatus;

public class HotelBookingException extends RuntimeException {
	

	private static final long serialVersionUID = 1L;
	private final HttpStatus code;
	private final String message;
	
	public HotelBookingException(String message, HttpStatus internalServerError) {
		this.code = internalServerError;
		this.message = message;
	}


	public HttpStatus getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}

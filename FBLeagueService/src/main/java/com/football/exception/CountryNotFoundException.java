package com.football.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="no such Country")
public class CountryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8934985482303669756L;

	public CountryNotFoundException(String message){
		super(message);
	}
}

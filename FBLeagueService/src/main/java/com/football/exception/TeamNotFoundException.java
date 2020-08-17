package com.football.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="no such Team")
public class TeamNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8209381200249850930L;

	public TeamNotFoundException(String message){
		super(message);
	}
}

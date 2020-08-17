package com.football.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="no such Team")
public class LeagueNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8934985482303669756L;

	public LeagueNotFoundException(String message){
		super(message);
	}
}

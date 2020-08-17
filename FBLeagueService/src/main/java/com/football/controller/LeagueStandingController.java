package com.football.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.football.exception.CountryNotFoundException;
import com.football.exception.LeagueNotFoundException;
import com.football.exception.TeamNotFoundException;
import com.football.model.ExceptionResponse;
import com.football.model.LeagueStandingResponseModel;
import com.football.service.LeagueStandingService;

@RestController
@RequestMapping("/football")
public class LeagueStandingController {

	@Autowired
	LeagueStandingService leagueStandingService;

	@GetMapping(value = "/standing", produces = "application/json")
	public LeagueStandingResponseModel getLeagueStanding(@RequestParam String countryName,
			@RequestParam String leagueName, @RequestParam String teamName) {
		LeagueStandingResponseModel responseModel = new LeagueStandingResponseModel();
		responseModel = leagueStandingService.getLeagueStanding(countryName, leagueName, teamName);
		return responseModel;
	}

	@ExceptionHandler(CountryNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleCountryNotFound(CountryNotFoundException exception,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(LeagueNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleLeagueNotFound(LeagueNotFoundException exception,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TeamNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleTeamNotFound(TeamNotFoundException exception,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(new Date(), exception.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}

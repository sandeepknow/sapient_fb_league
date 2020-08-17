package com.football.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.football.client.ClientApiService;
import com.football.exception.CountryNotFoundException;
import com.football.exception.LeagueNotFoundException;
import com.football.exception.TeamNotFoundException;
import com.football.model.Country;
import com.football.model.League;
import com.football.model.LeagueStandingPosition;
import com.football.model.LeagueStandingResponseModel;

@Service
public class LeagueStandingService {

	@Autowired
	ClientApiService clientApiService;

	public LeagueStandingResponseModel getLeagueStanding(String countryName, String leagueName, String teamName) {
		// TODO Auto-generated method stub

		LeagueStandingResponseModel response = new LeagueStandingResponseModel();
		Country country = findCountryByName(countryName);
		if (country == null)
			throw new CountryNotFoundException("Country Not Found");

		response.setCountryName(countryName);
		response.setCountryId(country.getCountryId());

		League league = findLeagueByCountryId(response.getCountryId());
		if (league == null)
			throw new LeagueNotFoundException("League Not Found");

		response.setLeagueName(leagueName);
		response.setLeagueId(league.getLeagueId());

		List<LeagueStandingPosition> leaguePositions = getStandingByLeague(response.getLeagueId());
		leaguePositions = leaguePositions.stream().filter(
				position -> position.getCountryName().equals(countryName) && position.getTeamName().equals(teamName))
				.collect(Collectors.toList());

		if(leaguePositions.isEmpty()) {
			throw new TeamNotFoundException("Team Not Found");
		}else {
			response.setTeamId(leaguePositions.get(0).getTeamId());
			response.setTeamName(leaguePositions.get(0).getTeamName());
			response.setStandingPosition(leaguePositions.get(0).getOverallLeaguePosition());
		}
		return response;
	}

	public Country findCountryByName(String countryName) {

		Optional<Country> countryOpt = clientApiService.getAllCountries().stream()
				.filter(x -> x.getCountryName().equals(countryName)).findFirst();

		return countryOpt.isPresent() ? countryOpt.get() : null;
	}

	public League findLeagueByCountryId(String countryId) {
		List<League> leagues = clientApiService.getLeagueByCountryId(countryId);
		Optional<League> leagueOpt = leagues.stream().filter(x -> x.getCountryId().equals(countryId)).findFirst();

		return leagueOpt.isPresent() ? leagueOpt.get() : null;
	}

	public List<LeagueStandingPosition> getStandingByLeague(String leagueId) {
		return clientApiService.getStandingByLeagueId(leagueId);
	}

}

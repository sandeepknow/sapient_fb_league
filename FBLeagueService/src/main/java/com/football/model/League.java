package com.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class League {

	@JsonProperty("country_id")
	private String countryId;

	@JsonProperty("league_id")
	private String leagueId;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

}

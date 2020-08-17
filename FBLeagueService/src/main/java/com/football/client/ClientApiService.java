package com.football.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.football.model.Country;
import com.football.model.League;
import com.football.model.LeagueStandingPosition;

@Service
public class ClientApiService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.endpoint.baseurl}")
	private String baseUrl;

	@Value("${api.action.countries:get_countries}")
	private String getCountriesAction;

	@Value("${api.action.leagues:get_leagues}")
	private String getLeaguesAction;

	@Value("${api.action.standings:get_standings}")
	private String getStandingAction;

	@Value("${api.endpoint.secret:9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978}")
	private String apiKey;

	public List<Country> getAllCountries() {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl)
				.queryParam("action", getCountriesAction).queryParam("APIkey", apiKey);

		ResponseEntity<List<Country>> countries = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Country>>() {
				});

		return countries.getBody();
	}

	public List<League> getLeagueByCountryId(String countryId) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl)
				.queryParam("action", getLeaguesAction).queryParam("country_id", countryId).queryParam("APIkey", apiKey);

		ResponseEntity<List<League>> countries = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<League>>() {
				});

		return countries.getBody();
	}

	public List<LeagueStandingPosition> getStandingByLeagueId(String leagueId) {
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl)
				.queryParam("action", getStandingAction).queryParam("league_id", leagueId).queryParam("APIkey", apiKey);

		ResponseEntity<List<LeagueStandingPosition>> leagues = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<List<LeagueStandingPosition>>() {
				});

		return leagues.getBody();
	}
}

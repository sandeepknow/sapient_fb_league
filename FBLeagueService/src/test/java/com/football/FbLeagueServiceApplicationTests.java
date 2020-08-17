package com.football;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.football.client.ClientApiService;
import com.football.model.Country;
import com.football.model.League;
import com.football.model.LeagueStandingPosition;
import com.football.model.LeagueStandingResponseModel;
import com.football.service.LeagueStandingService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class FbLeagueServiceApplicationTests {

	@MockBean
	ClientApiService clientApiService;

	@Autowired
	LeagueStandingService leagueStandingService;

	@Test
	void getLeagueResponse() throws Exception {
//		List<LeagueStandingPosition> standings = new ArrayList<LeagueStandingPosition>();
//		Mockito.when(clientApiService.getStandingByLeagueId("149")).thenReturn(standings);
//		Mockito.when(clientApiService.getLeagueByCountryId("41")).thenReturn(new ArrayList<League>());
		
		ArrayList<Country> countries = new ArrayList<Country>();
		Country country1 = new Country() ;
		country1.setCountryId("41");
		country1.setCountryName("England");
		countries.add(country1);
		
		Country country2 = new Country() ;
		country2.setCountryId("46");
		country2.setCountryName("France");
		countries.add(country2);
		
		Mockito.when(clientApiService.getAllCountries()).thenReturn(countries);

		Country returnedCountries = leagueStandingService.findCountryByName("England");
		assertTrue(returnedCountries.getCountryId().equals("41"));
	}

}

package edu.cnm.deepdive.teamle.service;

import edu.cnm.deepdive.teamle.model.dto.CountryResponse;
import edu.cnm.deepdive.teamle.model.dto.LeagueResponse;
import edu.cnm.deepdive.teamle.model.dto.TeamResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface SportsDBProxy {

  @GET("all_countries.php")
  Single<CountryResponse> getAllCountries();

  @GET("all_leagues.php")
  Single<LeagueResponse> getAllLeagues();

  @GET("all_teams.php")
  Single<TeamResponse> getAllTeams();
}

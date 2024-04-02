package edu.cnm.deepdive.teamle.service;

import edu.cnm.deepdive.teamle.model.dto.CountryResponse;
import edu.cnm.deepdive.teamle.model.dto.LeagueResponse;
import edu.cnm.deepdive.teamle.model.dto.TeamResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Used to get the API key to populate the game with needed information from the API
 */
public interface SportsDBProxy {

  @GET("{api_key}/all_countries.php")
  Single<CountryResponse> getAllCountries(@Path("api_key") String apiKey);

  @GET("{api_key}/all_leagues.php")
  Single<LeagueResponse> getAllLeagues(@Path("api_key") String apiKey);

  @GET("{api_key}/lookup_all_teams.php")
  Single<TeamResponse> getAllTeams(@Path("api_key") String apiKey,@Query("id") String leagueId);
}

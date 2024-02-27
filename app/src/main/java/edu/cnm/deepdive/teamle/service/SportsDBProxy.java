package edu.cnm.deepdive.teamle.service;

import edu.cnm.deepdive.teamle.model.dto.CountryResponse;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface SportsDBProxy {

  @GET("all_countries.php")
  Single<CountryResponse> getAllCountries();
}

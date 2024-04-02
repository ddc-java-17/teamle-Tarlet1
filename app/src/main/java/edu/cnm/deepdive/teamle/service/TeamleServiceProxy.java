package edu.cnm.deepdive.teamle.service;

import edu.cnm.deepdive.teamle.model.Game;
import edu.cnm.deepdive.teamle.model.Guess;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Gets games, game ID's, and the list of guesses from a specific game
 */
public interface TeamleServiceProxy {

  @POST("games")
  Single<Game> startGame(@Body Game game, @Header("Authorization") String bearerToken);

  @GET("games/{id}")
  Single<Game> getGame(@Path("id") String id, @Header("Authorization") String bearerToken);

  @POST("games/{id}/guesses")
  Single<Guess> submitGuess(@Body Guess guess, @Header("Authorization") String bearerToken);

}

package edu.cnm.deepdive.teamle.service;

import android.annotation.SuppressLint;
import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.model.Game;
import edu.cnm.deepdive.teamle.model.Guess;
import edu.cnm.deepdive.teamle.model.League;
import edu.cnm.deepdive.teamle.model.Team;
import edu.cnm.deepdive.teamle.model.dto.LeagueResponse;
import edu.cnm.deepdive.teamle.model.dto.TeamResponse;
import edu.cnm.deepdive.teamle.model.entity.GameResult;
import edu.cnm.deepdive.teamle.model.entity.User;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.Executors;
import javax.inject.Inject;

public class TeamleRepository {

  private final SportsDBProxy proxy;
  private final GameResultRepository resultRepository;
  private final UserRepository userRepository;
  private final Scheduler scheduler;
  private final String apiKey;

  private List<Team> teams;
  private List<League> leagues;
  private Game game;

  // TODO: 3/19/2024 Register as preferences listener so when league pref changes we can get list of teams for that league.
  @Inject
  public TeamleRepository(SportsDBProxy proxy, GameResultRepository resultRepository,
      UserRepository userRepository, @ApplicationContext Context context) {
    this.proxy = proxy;
    this.resultRepository = resultRepository;
    this.userRepository = userRepository;
    this.scheduler = Schedulers.from(Executors.newFixedThreadPool(4));
    apiKey= context.getString(R.string.api_key);
  }

  public Single<List<League>> getAllLeagues() {
    return proxy.getAllLeagues(apiKey)
        .map(LeagueResponse::getLeagues)
        .doOnSuccess((leagues) -> this.leagues = leagues)
        .subscribeOn(scheduler);
  }

  public Single<List<Team>> getAllTeamsByLeague(String leagueId) {
    return proxy.getAllTeams(apiKey, leagueId)
        .map(TeamResponse::getTeams)
        .doOnSuccess((teams) -> this.teams = teams)
        .subscribeOn(scheduler);
  }

  public void startGame(Game game) {
    // TODO: 3/19/2024 perform any necessary validation.
    this.game = game;
  }

  @SuppressLint("checkResult")
  public void submitGuess(String text) {
    // TODO: 3/19/2024 compare this text with correct text; take proper action.
    // TODO: 3/19/2024 create guess instance and add to list of guesses in game.
  }

  public Game getGame() {
    return game;
  }

  private GameResult toResult(Game game, User user) {
    return new GameResult();
  }
}

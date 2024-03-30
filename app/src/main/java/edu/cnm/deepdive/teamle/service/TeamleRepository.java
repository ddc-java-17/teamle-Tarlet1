package edu.cnm.deepdive.teamle.service;

import android.annotation.SuppressLint;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
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
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;

public class TeamleRepository {

  private final SportsDBProxy proxy;
  private final GameResultRepository resultRepository;
  private final UserRepository userRepository;
  private final Scheduler scheduler;
  private final String apiKey;
  private final Random rng;

  private List<Team> teams;
  private Map<String, List<League>> leaguesBySport;
  private Game game;

  // TODO: 3/19/2024 Register as preferences listener so when league pref changes we can get list of teams for that league.
  @Inject
  public TeamleRepository(SportsDBProxy proxy, GameResultRepository resultRepository,
      UserRepository userRepository, @ApplicationContext Context context, Random rng) {
    this.proxy = proxy;
    this.resultRepository = resultRepository;
    this.userRepository = userRepository;
    this.rng = rng;
    this.scheduler = Schedulers.from(Executors.newFixedThreadPool(4));
    apiKey = context.getString(R.string.api_key);
  }

  // TODO: 3/28/2024 get all sports, get all leagues in a specific sport.

  public Single<? extends Map<String, List<League>>> getAllLeaguesBySport() {
    return proxy.getAllLeagues(apiKey)
        .map(LeagueResponse::getLeagues)
        .map((leagues) -> leagues.stream()
            .collect(Collectors.groupingBy(League::getSport, TreeMap::new, Collectors.toList())))
        .doOnSuccess((map) -> map.values()
            .forEach((leagues) -> leagues.sort(Comparator.comparing(League::getName))))
        .doOnSuccess((map) -> leaguesBySport = map)
        .subscribeOn(scheduler);
  }

  public Single<List<Team>> getAllTeamsByLeague(String leagueId) {
    return proxy.getAllTeams(apiKey, leagueId)
        .map(TeamResponse::getTeams)
        .doOnSuccess((teams) -> this.teams = teams)
        .subscribeOn(scheduler);
  }

  public Team pick() {
    return teams.get(rng.nextInt(teams.size()));

  }

  public Game startGame() {
    Game game = new Game(pick());
    this.game = game;
    return game;
  }

  @SuppressLint("checkResult")
  public Guess submitGuess(Team pick) {
    if (game.isSolved()) {
      throw new IllegalStateException();
    }
    Guess guess = new Guess(pick, game.getCorrectAnswer());
    game.getGuesses().add(guess);
    if (guess.isCorrect()) {
       // TODO: 3/30/2024 create a game result instance and store in database.

    }
    return guess;
  }

  public Game getGame() {
    return game;
  }

  private GameResult toResult(Game game, User user) {
    return new GameResult();
  }
}

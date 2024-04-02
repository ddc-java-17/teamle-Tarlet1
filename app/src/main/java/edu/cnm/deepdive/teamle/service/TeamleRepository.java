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
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * This repository handles starting and playing a game.
 */
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

  /**
   * Handles all things needed for a game to be played
   *
   * @param proxy            SportsDBProxy
   * @param resultRepository GameResultRepository
   * @param userRepository   UserRepository
   * @param context          Context
   * @param rng              Random
   */
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

  /**
   * Gets all leagues by sport
   *
   * @return Leagues
   */
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

  /**
   * Gets all teams in a specific league
   *
   * @param leagueId String
   * @return List<Teams>
   */
  public Single<List<Team>> getAllTeamsByLeague(String leagueId) {
    return proxy.getAllTeams(apiKey, leagueId)
        .map(TeamResponse::getTeams)
        .doOnSuccess((teams) -> this.teams = teams)
        .subscribeOn(scheduler);
  }

  /**
   * Handles the team selected for a guess
   *
   * @return Team
   */
  public Team pick() {
    return teams.get(rng.nextInt(teams.size()));

  }

  /**
   * Handles the start of a game
   *
   * @return Game
   */
  public Game startGame() {
    Game game = new Game(pick());
    this.game = game;
    return game;
  }

  /**
   * Handles the submission of a guess
   *
   * @param pick Team
   * @return Guess
   */
  @SuppressLint("checkResult")
  public Guess submitGuess(Team pick) {
    if (game.isSolved()) {
      throw new IllegalStateException();
    }
    Guess guess = new Guess(pick, game.getCorrectAnswer());
    List<Guess> guesses = game.getGuesses();
    guesses.add(guess);
    if (guess.isCorrect()) {
      GameResult result = new GameResult();
      result.setSize(teams.size());
      result.setGuessCount(guesses.size());
      result.setDuration(
          Duration.between(guesses.get(0).created(), guesses.get(guesses.size() - 1).created()));
      resultRepository.add(result).subscribe();
    }
    return guess;
  }

  /**
   * Gets a game
   *
   * @return Game
   */
  public Game getGame() {
    return game;
  }

  private GameResult toResult(Game game, User user) {
    return new GameResult();
  }
}

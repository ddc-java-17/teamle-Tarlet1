package edu.cnm.deepdive.teamle.service;

import android.annotation.SuppressLint;
import edu.cnm.deepdive.teamle.model.Game;
import edu.cnm.deepdive.teamle.model.Guess;
import edu.cnm.deepdive.teamle.model.entity.GameResult;
import edu.cnm.deepdive.teamle.model.entity.User;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import javax.inject.Inject;

public class TeamleRepository {

  private final TeamleServiceProxy proxy;
  private final GameResultRepository resultRepository;
  private final UserRepository userRepository;
  private final GoogleSignInService signInService;
  private final Scheduler scheduler;

  private Game game;

  @Inject
  public TeamleRepository(TeamleServiceProxy proxy, GameResultRepository resultRepository,
      UserRepository userRepository,
      GoogleSignInService signInService, Scheduler scheduler) {
    this.proxy = proxy;
    this.resultRepository = resultRepository;
    this.userRepository = userRepository;
    this.signInService = signInService;
    this.scheduler = scheduler;
  }

  public Single<Game> startGame(Game game) {
    return signInService
        .refreshBearerToken()
        .observeOn(scheduler)
        .flatMap((token) -> proxy.startGame(game, token))
        .doOnSuccess(this::setGame);
  }

  @SuppressLint("checkResult")
  public Single<Guess> submitGuess(String text) {
    return signInService
        .refreshBearerToken()
        .observeOn(scheduler)
        .flatMap((token) -> proxy.submitGuess(game.validate(text), token))
        .flatMap((guess) -> {
          game.getGuesses().add(guess);
          return game.isSolved()
              ? userRepository
              .getCurrent()
              .map((user -> toResult(game, user)))
              .flatMap(resultRepository::add)
              .map((result) -> guess)
              : Single.just(guess);
        });
  }

  public Game getGame() {
    return game;
  }

  private void setGame(Game game) {
  }

  private GameResult toResult(Game game, User user) {
    return new GameResult();
  }
}

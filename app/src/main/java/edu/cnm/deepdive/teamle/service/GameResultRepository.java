package edu.cnm.deepdive.teamle.service;

import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.teamle.model.dao.GameResultDao;
import edu.cnm.deepdive.teamle.model.entity.GameResult;
import edu.cnm.deepdive.teamle.model.entity.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

/**
 * This repository holds all game results.
 */
public class GameResultRepository {

  private final GameResultDao gameResultDao;

  @Inject
  public GameResultRepository(GameResultDao gameResultDao) {
    this.gameResultDao = gameResultDao;
  }

  /**
   * Adds games results to List of game results
   *
   * @param gameResult GameResult
   * @return gameResultDao
   */
  public Single<GameResult> add(GameResult gameResult) {
    return gameResultDao
        .insertAndUpdate(gameResult)
        .subscribeOn(Schedulers.single());
  }

  /**
   * Gets all game results
   *
   * @param correctTeam int
   * @param user        User
   * @return ranked results
   */
  public LiveData<List<GameResult>> getAll(int correctTeam, User user) {
    return (user != null)
        ? gameResultDao.getRankedResults(correctTeam, user.getId())
        : gameResultDao.getRankedResults(correctTeam);
  }

  /**
   * clears game results
   *
   * @return gameResultDao
   */
  public Completable clear() {
    return gameResultDao
        .truncateResults()
        .subscribeOn(Schedulers.io());
  }

  /**
   * Gets all game results
   *
   * @return gameResultDao
   */
  public LiveData<List<GameResult>> getall() {
    return gameResultDao
        .getAllRankedResults();
  }

}

package edu.cnm.deepdive.teamle.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import edu.cnm.deepdive.teamle.model.entity.GameResult;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;

/**
 * Queries stats from games played for scores screen.
 */
@Dao
public interface GameResultDao {

  String RANKING_QUERY = "SELECT * "
      + "FROM game_result "
      + "WHERE size = :size "
      + "ORDER BY guess_count ASC, duration ASC";
  String RANKING_QUERY_FOR_RESULT = "SELECT * "
      + "FROM game_result "
      + "WHERE size = :size "
      + "AND user_id = :userId "
      + "ORDER BY guess_count ASC, duration ASC";
  String TRUNCATION_QUERY = "DELETE FROM game_result";

  @Insert
  Single<Long> insert(GameResult gameResult);

  @Query(RANKING_QUERY)
  LiveData<List<GameResult>> getRankedResults(int size);

  @Query("SELECT * FROM game_result ORDER BY size DESC, guess_count ASC, duration ASC")
  LiveData<List<GameResult>> getAllRankedResults();

  @Query(RANKING_QUERY_FOR_RESULT)
  LiveData<List<GameResult>> getRankedResults(int size, long userId);

  @Query(TRUNCATION_QUERY)
  Completable truncateResults();

  default Single<GameResult> insertAndUpdate(GameResult gameResult) {
    return insert(gameResult)
        .map((id) -> {
          gameResult.setId(id);
          return gameResult;
        });
  }
}



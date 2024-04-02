package edu.cnm.deepdive.teamle.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.teamle.model.entity.GameResult;
import edu.cnm.deepdive.teamle.service.GameResultRepository;
import java.util.List;
import javax.inject.Inject;

/**
 * Handles all game results
 */
@HiltViewModel
public class GameResultViewModel extends ViewModel {

  private final GameResultRepository repository;

  /**
   * holds game result objects
   *
   * @param repository GameResultRepository
   */
  @Inject
  public GameResultViewModel(GameResultRepository repository) {
    this.repository = repository;
  }

  /**
   * Gets all game results
   *
   * @return List<GameResult>
   */
  public LiveData<List<GameResult>> getall() {
    return repository.getall();
  }

}

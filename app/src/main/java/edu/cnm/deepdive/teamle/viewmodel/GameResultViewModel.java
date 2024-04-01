package edu.cnm.deepdive.teamle.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.teamle.model.entity.GameResult;
import edu.cnm.deepdive.teamle.service.GameResultRepository;
import java.util.List;
import javax.inject.Inject;

@HiltViewModel
public class GameResultViewModel extends ViewModel {

  private final GameResultRepository repository;

  @Inject
  public GameResultViewModel(GameResultRepository repository) {
    this.repository = repository;
  }

  public LiveData<List<GameResult>> getall() {
    return repository.getall();
  }

}

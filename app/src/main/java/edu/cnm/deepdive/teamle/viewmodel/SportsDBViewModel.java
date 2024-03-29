package edu.cnm.deepdive.teamle.viewmodel;

import android.util.Log;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import dagger.hilt.android.lifecycle.HiltViewModel;
import edu.cnm.deepdive.teamle.model.League;
import edu.cnm.deepdive.teamle.model.Team;
import edu.cnm.deepdive.teamle.service.TeamleRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

@HiltViewModel
public class SportsDBViewModel  extends ViewModel implements DefaultLifecycleObserver {

  private static final String TAG = SportsDBViewModel.class.getSimpleName();

  private final TeamleRepository teamleRepository;
  private final MutableLiveData<Map<String, List<League>>> leaguesBySport;
  private final MutableLiveData<List<Team>> teams;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  @Inject
  public SportsDBViewModel(TeamleRepository teamleRepository) {
    this.teamleRepository = teamleRepository;
    leaguesBySport = new MutableLiveData<>();
    teams = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    fetchLeagues();
  }

  public void fetchLeagues() {
    teamleRepository.getAllLeaguesBySport()
        .subscribe(
            leaguesBySport::postValue,
            this::postThrowable,
            pending
        );
  }

  public void fetchTeams(String leagueId) {
    // TODO: 3/29/2024 invoke the appropiate method in teamle repository to get teams, subscribe to result, populate live data.
  }

  public LiveData<Map<String, List<League>>> getLeaguesBySport() {
    return leaguesBySport;
  }

  public LiveData<List<Team>> getTeams() {
    return teams;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  @Override
  public void onStop(@NotNull LifecycleOwner owner) {
    pending.clear();
    DefaultLifecycleObserver.super.onStop(owner);
  }

  private void postThrowable(Throwable throwable) {
    Log.e(TAG, throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}

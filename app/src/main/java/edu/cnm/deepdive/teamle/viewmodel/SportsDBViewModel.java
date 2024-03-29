package edu.cnm.deepdive.teamle.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.model.League;
import edu.cnm.deepdive.teamle.model.Team;
import edu.cnm.deepdive.teamle.service.PreferencesRepository;
import edu.cnm.deepdive.teamle.service.TeamleRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

@HiltViewModel
public class SportsDBViewModel  extends ViewModel implements DefaultLifecycleObserver,
    OnSharedPreferenceChangeListener {

  private static final String TAG = SportsDBViewModel.class.getSimpleName();

  private final TeamleRepository teamleRepository;
  private final Map<String, List<League>> leaguesBySport;
  private final MutableLiveData<Set<String>> sports;
  private final MutableLiveData<List<League>> leagues;
  private final MutableLiveData<List<Team>> teams;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final SharedPreferences prefs;
  private final String sportKey;
  private final String leagueKey;


  @Inject
  public SportsDBViewModel(@ApplicationContext Context context, TeamleRepository teamleRepository) {
    this.teamleRepository = teamleRepository;
    leaguesBySport = new TreeMap<>();
    teams = new MutableLiveData<>();
    sports = new MutableLiveData<>();
    leagues = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    prefs = PreferenceManager.getDefaultSharedPreferences(context);
    sportKey = context.getString(R.string.sport_key);
    leagueKey = context.getString(R.string.league_key);
    fetchLeagues();
  }

  public void fetchLeagues() {
    teamleRepository.getAllLeaguesBySport()
        .subscribe(
            (map) -> {
              leaguesBySport.clear();
              leaguesBySport.putAll(map);
              sports.postValue(map.keySet());
              prefs.registerOnSharedPreferenceChangeListener(this);
            },
            this::postThrowable,
            pending
        );
  }

  public void fetchTeams(String leagueId) {
    if (leagueId != null) {
      teamleRepository.getAllTeamsByLeague(leagueId)
          .subscribe(
              teams::postValue,
              this::postThrowable,
              pending
          );
    }

    // TODO: 3/29/2024 invoke the appropiate method in teamle repository to get teams, subscribe to result, populate live data.
  }

  public LiveData<Set<String>> getSports() {
    return sports;
  }

  public LiveData<List<League>> getLeagues() {
    return leagues;
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

  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, @Nullable String key) {
    if (key.equals(sportKey)) {
      leagues.postValue(leaguesBySport.getOrDefault(prefs.getString(key, ""), List.of()));
    } else if (key.equals(leagueKey)) {
      fetchTeams(prefs.getString(leagueKey, "0"));
    }
  }
}

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
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.model.Game;
import edu.cnm.deepdive.teamle.model.Guess;
import edu.cnm.deepdive.teamle.model.League;
import edu.cnm.deepdive.teamle.model.Team;
import edu.cnm.deepdive.teamle.service.TeamleRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

/**
 * Handles everything needed from the API for a game
 */
@HiltViewModel
public class SportsDBViewModel extends ViewModel implements DefaultLifecycleObserver,
    OnSharedPreferenceChangeListener {

  private static final String TAG = SportsDBViewModel.class.getSimpleName();

  private final TeamleRepository teamleRepository;
  private final Map<String, List<League>> leaguesBySport;
  private final Map<String, League> idToLeague;
  private final MutableLiveData<Set<String>> sports;
  private final MutableLiveData<List<League>> leagues;
  private final MutableLiveData<List<Team>> teams;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<Game> game;
  private final LiveData<Guess> guess;
  private final CompositeDisposable pending;
  private final SharedPreferences prefs;
  private final String sportKey;
  private final String leagueKey;

  /**
   * Handles view model object
   * @param context Context
   * @param teamleRepository TeamleRepository
   */
  @Inject
  public SportsDBViewModel(@ApplicationContext Context context, TeamleRepository teamleRepository) {
    this.teamleRepository = teamleRepository;
    leaguesBySport = new TreeMap<>();
    idToLeague = new HashMap<>();
    teams = new MutableLiveData<>();
    sports = new MutableLiveData<>();
    leagues = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    prefs = PreferenceManager.getDefaultSharedPreferences(context);
    sportKey = context.getString(R.string.sport_key);
    leagueKey = context.getString(R.string.league_key);
    String preferredLeague = prefs.getString(context.getString(R.string.league_key), "");
    game = new MutableLiveData<>();
    guess = Transformations.map(game, (game) -> {
      List<Guess> guesses = game.getGuesses();
      return (game != null) ? guesses.get(guesses.size() - 1) : null;
    });
    fetchLeagues();
    if (!preferredLeague.isEmpty()) {
      fetchTeams(preferredLeague);
    }

  }

  /**
   * Gets all leagues in a specific sport
   */
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

  /**
   * Gets all teams in a specific league
   * @param leagueId String
   */
  public void fetchTeams(String leagueId) {
    if (leagueId != null) {
      teamleRepository.getAllTeamsByLeague(leagueId)
          .subscribe(
              teams::postValue,
              this::postThrowable,
              pending
          );
    }

  }

  /**
   * Gets all leagues by ID
   * @param id String
   * @return leagueId
   */
  public League getLeagueById(String id) {
    return idToLeague.get(id);
  }

  /**
   * Starts a game
   */
  public void startGame() {
    game.postValue(teamleRepository.startGame());
  }

  /**
   * Submits a guess
   * @param team Team
   */
  public void submitGuess(Team team) {
    Log.d(TAG, team.toString());
    teamleRepository.submitGuess(team);
    game.postValue(game.getValue());
  }

  /**
   * Gets all sports
   * @return sports
   */
  public LiveData<Set<String>> getSports() {
    return sports;
  }

  /**
   * Gets all leagues
   * @return leagues
   */
  public LiveData<List<League>> getLeagues() {
    return leagues;
  }

  /**
   * Gets all teams
   * @return teams
   */
  public LiveData<List<Team>> getTeams() {
    return teams;
  }

  /**
   * Gets games
   * @return game
   */
  public LiveData<Game> getGame() {
    return game;
  }

  /**
   * Gets guess
   * @return guess
   */
  public LiveData<Guess> getGuess() {
    return guess;
  }

  /**
   * gets a throwable
   * @return throwable
   */
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
    //noinspection DataFlowIssue
    if (key.equals(sportKey)) {
      List<League> leagues = leaguesBySport.getOrDefault(prefs.getString(key, ""), List.of());
      idToLeague.clear();
      //noinspection DataFlowIssue
      idToLeague.putAll(
          leagues.stream().collect(Collectors.toMap(League::getId, Function.identity())));
      this.leagues.postValue(leagues);
    } else if (key.equals(leagueKey)) {
      fetchTeams(prefs.getString(leagueKey, "0"));
    }
  }
}

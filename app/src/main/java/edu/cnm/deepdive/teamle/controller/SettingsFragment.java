package edu.cnm.deepdive.teamle.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.DropDownPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.model.League;
import edu.cnm.deepdive.teamle.viewmodel.SportsDBViewModel;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Presents a standard settings screen, populated from the
 * {@link androidx.preference.PreferenceScreen} declared in {@code res/xml/settings.xml}.
 */
@AndroidEntryPoint
public class SettingsFragment extends PreferenceFragmentCompat {

  private DropDownPreference sportPreference;
  private DropDownPreference leaguePreference;
  private SportsDBViewModel viewModel;
  private String preferredSport;
  private String preferredLeague;

  /**
   * @noinspection DataFlowIssue
   */
  @Override
  public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
    preferredSport = preferences.getString(requireContext().getString(R.string.sport_key), "");
    preferredLeague = preferences.getString(requireContext().getString(R.string.league_key), "");
    // TODO: 3/30/2024 Grab current preferences.
    setPreferencesFromResource(R.xml.settings, rootKey);
    sportPreference = findPreference(getString(R.string.sport_key));
    sportPreference.setOnPreferenceChangeListener((prefs, value) -> {
      prefs.setSummary(value.toString());
      preferredSport = value.toString();
      return true;
    }) ;
    leaguePreference = findPreference(getString(R.string.league_key));
    leaguePreference.setOnPreferenceChangeListener((prefs, value) -> {
      String id = (String) value;
      League league = viewModel.getLeagueById(id);
      prefs.setSummary(league.getName());
      preferredLeague = id;
      return true;
    });

  }

  @Override
  public void onStart() {
    super.onStart();
    viewModel = new ViewModelProvider(requireActivity()).get(SportsDBViewModel.class);
    viewModel.getSports()
        .observe(getViewLifecycleOwner(), (sports) -> {
          String[] sportNames = sports
              .stream()
              .toArray(String[]::new);
          sportPreference.setEntries(sportNames);
          sportPreference.setEntryValues(sportNames);
          sportPreference.setValue(preferredSport);
        });
    viewModel.getLeagues()
        .observe(getViewLifecycleOwner(), (leagues) -> {
          leaguePreference.setEntries(leagues.stream().map(League::getName).toArray(String[]::new));
          leaguePreference.setEntryValues(leagues.stream().map(League::getId).toArray(String[]::new));
          leaguePreference.setValue(preferredLeague);
        });
    viewModel.getTeams()
        .observe(getViewLifecycleOwner(), (teams) -> {
          Log.d(getClass().getSimpleName(), String.valueOf(teams.size()));
        });
  }

}

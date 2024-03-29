package edu.cnm.deepdive.teamle.controller;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.DropDownPreference;
import androidx.preference.PreferenceFragmentCompat;
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

  /**
   * @noinspection DataFlowIssue
   */
  @Override
  public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
    setPreferencesFromResource(R.xml.settings, rootKey);
    leaguePreference = findPreference(getString(R.string.league_key));
    sportPreference = findPreference(getString(R.string.sport_key));
  }

  @Override
  public void onStart() {
    super.onStart();
    SportsDBViewModel viewModel = new ViewModelProvider(requireActivity()).get(SportsDBViewModel.class);
    viewModel.getSports()
        .observe(getViewLifecycleOwner(), (sports) -> {
          String[] sportNames = sports
              .stream()
              .toArray(String[]::new);
          sportPreference.setEntries(sportNames);
          sportPreference.setEntryValues(sportNames);
        });
    viewModel.getLeagues()
        .observe(getViewLifecycleOwner(), (leagues) -> {
          leaguePreference.setEntries(leagues.stream().map(League::getName).toArray(String[]::new));
          leaguePreference.setEntryValues(leagues.stream().map(League::getId).toArray(String[]::new));
        });
    viewModel.getTeams()
        .observe(getViewLifecycleOwner(), (teams) -> {
          Log.d(getClass().getSimpleName(), String.valueOf(teams.size()));
        });
  }

}

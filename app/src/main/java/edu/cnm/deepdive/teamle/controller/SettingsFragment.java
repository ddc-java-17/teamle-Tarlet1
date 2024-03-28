package edu.cnm.deepdive.teamle.controller;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.DropDownPreference;
import androidx.preference.PreferenceFragmentCompat;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.hilt.SportsDBModule;
import edu.cnm.deepdive.teamle.hilt.SportsDBModule_Proxy;
import edu.cnm.deepdive.teamle.model.League;
import edu.cnm.deepdive.teamle.service.SportsDBProxy;
import edu.cnm.deepdive.teamle.viewmodel.SportsDBViewModel;
import hilt_aggregated_deps._edu_cnm_deepdive_teamle_hilt_SportsDBModule;

/**
 * Presents a standard settings screen, populated from the
 * {@link androidx.preference.PreferenceScreen} declared in {@code res/xml/settings.xml}.
 */
@AndroidEntryPoint
public class SettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
    setPreferencesFromResource(R.xml.settings, rootKey);
    DropDownPreference preference = findPreference(getString(R.string.league_key));
    SportsDBViewModel viewModel = new ViewModelProvider(this).get(SportsDBViewModel.class);
    viewModel.getLeagues()
        .observe(this, (leagues) -> {
          preference.setEntries(
              leagues.stream()
                  .map(League::getName)
                  .toArray(String[]::new)
          );
          preference.setEntryValues(
              leagues.stream()
                  .map(League::getId)
                  .toArray(String[]::new)
          );
        });
    // TODO: 3/19/2024 Populate the array from sportsDB league query.
    //noinspection DataFlowIssue
    //preference.setValue("F");
  }



}

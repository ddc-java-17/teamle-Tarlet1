package edu.cnm.deepdive.teamle.controller;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.preference.DropDownPreference;
import androidx.preference.PreferenceFragmentCompat;
import edu.cnm.deepdive.teamle.R;

/**
 * Presents a standard settings screen, populated from the
 * {@link androidx.preference.PreferenceScreen} declared in {@code res/xml/settings.xml}.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

  @Override
  public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
    setPreferencesFromResource(R.xml.settings, rootKey);
    DropDownPreference preference = findPreference(getString(R.string.league_key));
    // TODO: 3/19/2024 Populate the array from sportsDB league query.
    //noinspection DataFlowIssue
    preference.setEntries(new String[]{"NHL", "MLB", "NFL"});
    preference.setEntryValues(new String[]{"H", "B", "F"});
    preference.setValue("F");
  }



}

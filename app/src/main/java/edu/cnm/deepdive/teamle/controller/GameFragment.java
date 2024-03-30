/*
 *  Copyright 2024 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package edu.cnm.deepdive.teamle.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.adapter.GuessesAdapter;
import edu.cnm.deepdive.teamle.databinding.FragmentGameBinding;
import edu.cnm.deepdive.teamle.model.Team;
import edu.cnm.deepdive.teamle.viewmodel.LoginViewModel;
import edu.cnm.deepdive.teamle.viewmodel.PermissionsViewModel;
import edu.cnm.deepdive.teamle.viewmodel.PreferencesViewModel;
import edu.cnm.deepdive.teamle.viewmodel.SportsDBViewModel;
import edu.cnm.deepdive.teamle.viewmodel.SportsDBViewModel_Factory;
import edu.cnm.deepdive.teamle.viewmodel.UserViewModel;

/**
 * Demonstrates access to and observation of {@link androidx.lifecycle.LiveData} elements in
 * {@link LoginViewModel}, {@link UserViewModel}, {@link PermissionsViewModel}, and
 * {@link PreferencesViewModel}, as well as acting as a navigation placeholder. This fragment can be
 * used as an example for creating other navigable fragments that access these core viewmodels; it
 * can then be evolved to provide more application-specific utility, or removed/replaced
 * altogether.
 */
@AndroidEntryPoint
public class GameFragment extends Fragment implements MenuProvider {

  private FragmentGameBinding binding;
  private SportsDBViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentGameBinding.inflate(inflater, container, false);
    binding.guessText.setOnItemSelectedListener(new OnItemSelectedListener() {

      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Team selectedTeam = (Team) parent.getSelectedItem();
        viewModel.submitGuess(selectedTeam);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });

    binding.guessText.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Team selectedTeam = (Team) parent.getItemAtPosition(position);
        viewModel.submitGuess(selectedTeam);
      }
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(requireActivity()).get(
        SportsDBViewModel.class);
    viewModel.getTeams()
        .observe(getViewLifecycleOwner(), (teams) -> {
          ArrayAdapter<Team> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, teams);
          binding.guessText.setAdapter(adapter);
        });
    viewModel.getGame()
            .observe(getViewLifecycleOwner(), (game) -> {
              binding.guessText.setText("");
              GuessesAdapter adapter = new GuessesAdapter(requireContext(), game.getGuesses());
              binding.guesses.setAdapter(adapter);
              if (game.isSolved()) {
                // TODO: 3/30/2024 display a pop up saying something.
                binding.guessText.setEnabled(false);
              } else {
                binding.guessText.setEnabled(true);
                if (!game.getGuesses().isEmpty()) {
              // TODO: 3/30/2024 display info about how close guess was.
                }
              }
            });
    requireActivity().addMenuProvider(this, getViewLifecycleOwner(), State.RESUMED);
  }

  @Override
  public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
    menuInflater.inflate(R.menu.game_options, menu);
  }

  @Override
  public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
    boolean handled = false;
    if (menuItem.getItemId() == R.id.new_game) {
      viewModel.startGame();
      handled = true;
    }
   return handled;
  }

}

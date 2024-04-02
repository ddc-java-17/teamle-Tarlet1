package edu.cnm.deepdive.teamle.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.teamle.adapter.ScoresAdapter;
import edu.cnm.deepdive.teamle.viewmodel.GameResultViewModel;

/**
 * Handles and inflates the scores screen.
 */
@AndroidEntryPoint
public class ScoresFragment extends Fragment {

  private edu.cnm.deepdive.teamle.databinding.FragmentScoresBinding binding;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = edu.cnm.deepdive.teamle.databinding.FragmentScoresBinding.inflate(inflater, container,
        false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    new ViewModelProvider(this)
        .get(GameResultViewModel.class)
        .getall()
        .observe(getViewLifecycleOwner(), (results) -> {
          ScoresAdapter adapter = new ScoresAdapter(requireContext(), results);
          binding.scores.setAdapter(adapter);
        });
  }
}

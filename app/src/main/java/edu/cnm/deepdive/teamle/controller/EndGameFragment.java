package edu.cnm.deepdive.teamle.controller;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.teamle.R;

/**
 * This Fragment is used to inflate the end of game message screen when a game is completed.
 */
@AndroidEntryPoint
public class EndGameFragment extends DialogFragment {

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    return new AlertDialog.Builder(requireContext())
        .setTitle(R.string.success_label)
        .setView(R.layout.fragment_end_game)
        .create();
  }

}

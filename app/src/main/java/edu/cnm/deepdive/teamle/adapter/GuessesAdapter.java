package edu.cnm.deepdive.teamle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.databinding.ItemGuessesBinding;
import edu.cnm.deepdive.teamle.model.Guess;
import java.util.List;

public class GuessesAdapter extends ArrayAdapter<Guess> {

  private final LayoutInflater inflater;

  public GuessesAdapter(@NonNull Context context, @NonNull List<Guess> guesses) {
    super(context, R.layout.item_guesses, guesses);
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    Guess guess = getItem(position);
    ItemGuessesBinding binding = (convertView != null)
        ? ItemGuessesBinding.bind(convertView)
        : ItemGuessesBinding.inflate(inflater, parent, false);
    binding.teamName.setText(guess.pick().getName());
    // TODO: 3/30/2024 populate other widgets as necessary.
    return binding.getRoot();
  }
}

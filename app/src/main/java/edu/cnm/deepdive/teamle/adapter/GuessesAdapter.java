package edu.cnm.deepdive.teamle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.google.android.material.color.MaterialColors;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.databinding.ItemGuessesBinding;
import edu.cnm.deepdive.teamle.model.Guess;
import java.util.List;

/**
 * Public class that extends ArrayAdapter and works as a GuessesAdapter.
 */
public class GuessesAdapter extends ArrayAdapter<Guess> {

  private final LayoutInflater inflater;
  @ColorInt
  private final int correct;
  @ColorInt
  private final int incorrect;

  /**
   * This adapter is used to change the background color of the hints for the team you guessed in a game.
   * @param context context
   * @param guesses List<guesses>
   */
  public GuessesAdapter(@NonNull Context context, @NonNull List<Guess> guesses) {
    super(context, R.layout.item_guesses, guesses);
    inflater = LayoutInflater.from(context);
    correct = ContextCompat.getColor(context, R.color.correct);
    incorrect = ContextCompat.getColor(context, R.color.incorrect);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    Guess guess = getItem(position);
    ItemGuessesBinding binding = (convertView != null)
        ? ItemGuessesBinding.bind(convertView)
        : ItemGuessesBinding.inflate(inflater, parent, false);
    binding.teamName.setText(guess.pick().getName());
    binding.teamName.setBackgroundColor(
        guess.pick().getName().equals(guess.secret().getName()) ? correct : incorrect);
    binding.teamLocation.setText(guess.pick().getLocation());
    binding.teamLocation.setBackgroundColor(
        guess.pick().getLocation().equals(guess.secret().getLocation()) ? correct : incorrect);
    binding.yearFormed.setText(String.valueOf(guess.pick().getYearCreated()));
    binding.yearFormed.setBackgroundColor(
        guess.pick().getYearCreated() == guess.secret().getYearCreated() ? correct : incorrect);
    binding.kitColor.setText(guess.pick().getPrimaryColor());
    binding.kitColor.setBackgroundColor(
        guess.pick().getPrimaryColor().equals(guess.secret().getPrimaryColor()) ? correct : incorrect);
    return binding.getRoot();
  }
}

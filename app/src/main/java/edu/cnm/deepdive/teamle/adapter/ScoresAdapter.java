package edu.cnm.deepdive.teamle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.teamle.R;
import edu.cnm.deepdive.teamle.controller.ScoresFragment;
import edu.cnm.deepdive.teamle.databinding.FragmentScoresBinding;
import edu.cnm.deepdive.teamle.databinding.ItemScoresBinding;
import edu.cnm.deepdive.teamle.model.entity.GameResult;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

/**
 * This class is an adapter for scores and extends ArrayAdapter
 */
public class ScoresAdapter extends ArrayAdapter<GameResult> {

  private final LayoutInflater inflater;
  private final String durationFormat;
  private final DateTimeFormatter timeStampFormat;

  /**
   * This adapter inflates and formats the duration and timestamp on the scores page.
   * @param context context
   * @param scores List<GameResult> scores
   */
  public ScoresAdapter(@NonNull Context context, List<GameResult> scores) {
    super(context, R.layout.fragment_scores, scores);
    inflater = LayoutInflater.from(context);
    durationFormat = context.getString(R.string.duration_format);
    timeStampFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    GameResult score = getItem(position);
    ItemScoresBinding binding = (convertView != null)
        ? ItemScoresBinding.bind(convertView)
        : ItemScoresBinding.inflate(inflater, parent, false);
    binding.leagueSize.setText(String.valueOf(score.getSize()));
    int ms = (int) score.getDuration().toMillis();
    double seconds = ms / 1000D;
    int minutes = (int) (seconds / 60);
    seconds %= 60;
    int hours = minutes / 60;
    minutes %= 60;
    binding.duration.setText(String.format(durationFormat, hours, minutes, seconds));
    binding.numberOfGuesses.setText(String.valueOf(score.getGuessCount()));
    LocalDateTime time = LocalDateTime.ofInstant(score.getTimestamp(), ZoneId.systemDefault());
    binding.timestamp.setText(timeStampFormat.format(time));
    return binding.getRoot();
  }
}

package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.reactivex.rxjava3.core.SingleSource;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Game {

  private final List<Guess> guesses;

  private final Team correctAnswer;

  private final Date start;

  public Game(List<Guess> guesses, Team correctAnswer, Date start) {
    this.guesses = guesses;
    this.correctAnswer = correctAnswer;
    this.start = start;
  }

  public List<Guess> getGuesses() {
    return guesses;
  }

  public Date getStart() {
    return start;
  }

  public Guess validate(String content) {
    // TODO: 3/18/2024 evaluate content against correctAnswer.getname.
   // return new Guess();
    throw new UnsupportedOperationException();
  }

  public boolean isSolved() {
    return guesses
        .stream()
        .anyMatch(guess -> guess.getContent().equals(correctAnswer.getName()));
  }

}
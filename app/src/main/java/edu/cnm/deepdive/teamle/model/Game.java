package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.reactivex.rxjava3.core.SingleSource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Game {

  private final List<Guess> guesses;
  private final Team correctAnswer;
  private final Date start;

  public Game(Team correctAnswer) {
    this.correctAnswer = correctAnswer;
    guesses = new LinkedList<>();
    start = new Date();
  }

  public List<Guess> getGuesses() {
    return guesses;
  }

  public Date getStart() {
    return start;
  }

  public Team getCorrectAnswer() {
    return correctAnswer;
  }

  public boolean isSolved() {
    return guesses.stream().anyMatch((Guess::isCorrect));

  }

}
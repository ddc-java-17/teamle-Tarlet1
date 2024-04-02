package edu.cnm.deepdive.teamle.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles all stats about a game
 */
public class Game {

  private final List<Guess> guesses;
  private final Team correctAnswer;
  private final Date start;

  /**
   * Sets the secret team
   *
   * @param correctAnswer Team
   */
  public Game(Team correctAnswer) {
    this.correctAnswer = correctAnswer;
    guesses = new LinkedList<>();
    start = new Date();
  }

  /**
   * gets the list of guesses submitted.
   *
   * @return List<Guesses>
   */
  public List<Guess> getGuesses() {
    return guesses;
  }

  /**
   * Gets the start time of a game
   *
   * @return Date
   */
  public Date getStart() {
    return start;
  }

  /**
   * Gets the correct answer for a game.
   *
   * @return Team
   */
  public Team getCorrectAnswer() {
    return correctAnswer;
  }

  /**
   * Checks if the game has been solved.
   *
   * @return boolean
   */
  public boolean isSolved() {
    return guesses.stream().anyMatch((Guess::isCorrect));

  }

}
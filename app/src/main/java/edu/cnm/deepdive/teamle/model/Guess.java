package edu.cnm.deepdive.teamle.model;

import java.time.Instant;

/**
 * Holds all guesses for a game.
 *
 * @param pick
 * @param secret
 * @param created
 */
public record Guess(Team pick, Team secret, Instant created) {

  /**
   * Guess object
   *
   * @param pick   Team
   * @param secret Team
   */
  public Guess(Team pick, Team secret) {
    this(pick, secret, Instant.now());
  }

  /**
   * Checks to see if a guess is the correct answer.
   *
   * @return boolean
   */
  public boolean isCorrect() {
    return pick.getId().equals(secret.getId());
  }


}

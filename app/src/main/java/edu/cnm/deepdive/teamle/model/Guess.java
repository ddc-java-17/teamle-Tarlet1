package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import java.time.Instant;
import java.util.Date;

public record Guess(Team pick, Team secret, Instant created) {

  public Guess (Team pick, Team secret) {
    this(pick, secret, Instant.now());
  }

  public boolean isCorrect() {
    return pick.getId().equals(secret.getId());
  }


}

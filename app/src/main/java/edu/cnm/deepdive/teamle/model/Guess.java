package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import java.util.Date;

public record Guess(Team pick, Team secret) {

  public boolean isCorrect() {
    return pick.getId().equals(secret.getId());
  }


}

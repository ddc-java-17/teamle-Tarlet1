package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Game {

  @Expose(serialize = false, deserialize = true)
  private final String id;

  @Expose
  private final String pool;

  @Expose(serialize = false, deserialize = true)
  private final List<Guess> guesses;

  private final Team correctAnswer;

  @Expose(serialize = false, deserialize = true)
  @SerializedName("created")
  private final Date start;

  @Expose
  private final List<League> leagues;

  public Game(String id, String pool, List<Guess> guesses, Team correctAnswer, Date start, List<League> leagues) {
    this.id = id;
    this.pool = pool;
    this.guesses = guesses;
    this.correctAnswer = correctAnswer;
    this.start = start;
    this.leagues = leagues;
  }

  public String getId() {
    return id;
  }

  public String getPool() {
    return pool;
  }

  public List<Guess> getGuesses() {
    return guesses;
  }

  public List<League> getLeagues() {
    return leagues;
  }

  public Date getStart() {
    return start;
  }


}
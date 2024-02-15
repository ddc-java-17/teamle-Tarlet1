package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class Guess {

  @Expose(serialize = false, deserialize = true)
  private final String id;

  @Expose
  private final String content;

  @Expose(serialize = false, deserialize = true)
  private final int correct;

  @Expose(serialize = false, deserialize = true)
  private final Date timestamp;

  private final Team team;

  public Guess(String id, String content, int correct, int close, Date timestamp) {
    this.id = id;
    this.content = content;
    this.correct = correct;
    this.close = close;
    this.timestamp = timestamp;
  }

  public String getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public int getCorrect() {
    return correct;
  }

  public int getClose() {
    return close;
  }

  public Date getTimestamp() {
    return timestamp;
  }


}

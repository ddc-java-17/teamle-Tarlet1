package edu.cnm.deepdive.teamle.model;

public class Team {

  public enum Result {
    CLOSE,
    CORRECT,
    WRONG
  }

  private final League league;

  private final String name;

  private final int numberOfPlayers;

  public Result compareNumberOfPlayers(Team team) {
    if (team.numberOfPlayers - this.numberOfPlayers < 3) {
      return Result.CLOSE;
    }
  }



}

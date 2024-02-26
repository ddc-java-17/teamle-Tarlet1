package edu.cnm.deepdive.teamle.model;

import java.util.List;

public class League {

  private String name;

  private final List<Team> teams;

  public League(List<Team> teams) {
    this.teams = teams;
  }
}

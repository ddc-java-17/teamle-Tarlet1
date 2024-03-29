package edu.cnm.deepdive.teamle.model;

public class Team {

  private final League league;
  private final String name;
  private final int Location;
  private final int numberOfChampionships;
  private final String division;

  public Team(League league, String name, int location, int numberOfChampionships,
      String division) {
    this.league = league;
    this.name = name;
    Location = location;
    this.numberOfChampionships = numberOfChampionships;
    this.division = division;
  }

  public League getLeague() {
    return league;
  }

  public String getName() {
    return name;
  }

  public int getLocation() {
    return Location;
  }

  public int getNumberOfChampionships() {
    return numberOfChampionships;
  }

  public String getDivision() {
    return division;
  }

  public enum Result {
    CLOSE,
    CORRECT,
    WRONG
  }


}







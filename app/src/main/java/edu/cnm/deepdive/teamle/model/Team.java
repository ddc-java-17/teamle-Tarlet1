package edu.cnm.deepdive.teamle.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

  @Expose
  @SerializedName("strTeam")
  private final String name;
  @Expose
  @SerializedName("strStadiumLocation")
  private final String location;
  private final int numberOfChampionships;
  @Expose
  private final String division;

  public Team(String name, String location, int numberOfChampionships,
      String division) {
    this.name = name;
    this.location = location;
    this.numberOfChampionships = numberOfChampionships;
    this.division = division;
  }


  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public int getNumberOfChampionships() {
    return numberOfChampionships;
  }

  public String getDivision() {
    return division;
  }

  @NonNull
  @Override
  public String toString() {
    return name;
  }

  public enum Result {
    CLOSE,
    CORRECT,
    WRONG
  }


}







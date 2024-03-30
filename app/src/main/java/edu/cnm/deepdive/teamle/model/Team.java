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
  @Expose
  @SerializedName("intFormedYear")
  private final int yearCreated;
  @Expose
  @SerializedName("strKitColour1")
  private final String primaryColor;
  @Expose
  @SerializedName("idTeam")
private final String id;

  public Team(String name, String location, int yearCreated,
      String primaryColor, String id) {
    this.name = name;
    this.location = location;
    this.primaryColor = primaryColor;
    this.yearCreated = yearCreated;
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public int getYearCreated() {
    return yearCreated;
  }

  public String getPrimaryColor() {
    return primaryColor;
  }

  public String getId() {
    return id;
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







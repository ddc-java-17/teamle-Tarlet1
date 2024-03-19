package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class League {

  @Expose
  @SerializedName("idLeague")
  private final String id = null;

  @Expose
  @SerializedName("strSport")
  private final String sport = null;

  @Expose
  @SerializedName("strLeague")
  private final String name = null;

  private final List<Team> teams = new ArrayList<>();

  public String getId() {
    return id;
  }

  public String getSport() {
    return sport;
  }

  public String getName() {
    return name;
  }

  public List<Team> getTeams() {
    return teams;
  }
}

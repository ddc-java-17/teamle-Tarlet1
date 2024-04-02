package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all league objects.
 */
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

  /**
   * Gets the league ID
   * @return String id
   */
  public String getId() {
    return id;
  }

  /**
   * gets the selected sport
   * @return String sport
   */
  public String getSport() {
    return sport;
  }

  /**
   * Gets the league name.
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets all teams in selected league.
   * @return List<Team>
   */
  public List<Team> getTeams() {
    return teams;
  }
}

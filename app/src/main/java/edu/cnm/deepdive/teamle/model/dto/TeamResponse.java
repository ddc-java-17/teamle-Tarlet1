package edu.cnm.deepdive.teamle.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamle.model.Team;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class TeamResponse {

  @Expose
  private final List<Team> teams = new LinkedList<>();

  /**
   * gets list of all teams in a specific league.
   * @return teams List<Team>
   */
  public List<Team> getTeams() {
    return teams;
  }

}

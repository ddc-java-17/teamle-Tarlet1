package edu.cnm.deepdive.teamle.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamle.model.League;
import java.util.LinkedList;
import java.util.List;

/**
 *This maps to a JSON object containing a list of league objects from the Sports DB API.
 */
public class LeagueResponse {

  @Expose
  private final List<League> leagues = new LinkedList<>();

  /**
   * Gets all leagues from the sports DB API
   * @return List<Leagues>
   */
  public List<League> getLeagues() {
    return leagues;
  }

}

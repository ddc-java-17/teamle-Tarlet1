package edu.cnm.deepdive.teamle.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamle.model.League;
import java.util.LinkedList;
import java.util.List;

public class LeagueResponse {

  @Expose
  private final List<League> leagues = new LinkedList<>();

  public List<League> getLeagues() {
    return leagues;
  }

}

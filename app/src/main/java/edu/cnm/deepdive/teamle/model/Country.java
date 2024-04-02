package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.LinkedList;
import java.util.List;

/**
 * Gets all sports/ leagues from a specific country.
 */
public class Country {

  @Expose
  @SerializedName("name_en")
  private final String name;
  private final List<League> leagues = new LinkedList<>();

  /**
   * Gets a specific country
   */
  public Country() {
    name = null;
  }

  /**
   * gets name of selected country
   *
   * @return String name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets list of leagues in a specific country
   *
   * @return List<Leagues>
   */
  public List<League> getLeagues() {
    return leagues;
  }
}

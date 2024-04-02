package edu.cnm.deepdive.teamle.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamle.model.Country;
import java.util.LinkedList;
import java.util.List;

/**
 * This maps to a JSOn object containing a list of Country objects from the Sports DB API.
 */
public class CountryResponse {

  @Expose
  private final List<Country> countries = new LinkedList<>();

  /**
   * Gets a list of countries from the sport DB API
   *
   * @return Liat<Country>
   */
  public List<Country> getCountries() {
    return countries;
  }
}

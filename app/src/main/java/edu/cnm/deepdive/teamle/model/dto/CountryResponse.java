package edu.cnm.deepdive.teamle.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamle.model.Country;
import java.util.LinkedList;
import java.util.List;

public class CountryResponse {

  @Expose
  private final List<Country> countries = new LinkedList<>();

  public List<Country> getCountries() {
    return countries;
  }
}

package edu.cnm.deepdive.teamle.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.LinkedList;
import java.util.List;

public class Country {

  @Expose
  @SerializedName("name_en")
  private final String name;
  private final List<League> leagues = new LinkedList<>();


  public Country() {
    name = null;
  }

  public String getName() {
    return name;
  }

  public List<League> getLeagues() {
    return leagues;
  }
}

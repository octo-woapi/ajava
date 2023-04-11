package com.octo.ajava.infra.api_client.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginatedTMDBMovies {

  private final int page;

  private final List<TMDBMovie> movies;

  private final int totalResults;

  private final int totalPages;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public PaginatedTMDBMovies(
      @JsonProperty("page") int page,
      @JsonProperty("results") List<TMDBMovie> movies,
      @JsonProperty("total_results") int totalResults,
      @JsonProperty("total_pages") int totalPages) {
    this.page = page;
    this.movies = movies;
    this.totalResults = totalResults;
    this.totalPages = totalPages;
  }

  public int getPage() {
    return page;
  }

  public List<TMDBMovie> getMovies() {
    return movies;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public int getTotalPages() {
    return totalPages;
  }
}

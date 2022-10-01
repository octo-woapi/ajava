package com.octo.ajava.infra.client.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * Utilisation des annotations de Jackson : @JsonIgnoreProperties(ignoreUnknown = true) = ne pas
 * échouer si une propriété n'existe pas en JAVA @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) =
 * à mettre pour que Jackson utilise le constructeur @JsonProperty("total_results") = mapping entre
 * le nom en JSON et le champ en JAVA
 *
 * <p>Interet du constructeur ? Avoir des classes immutables. Interet des annotations des propriétés
 * JSON ? Découpler le naming JSON et le naming JAVA
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TMDBMovie {

  private final int id;

  private final String originalTitle;

  private final String originalLanguage;

  private final String title;

  private final String overview;

  private final LocalDate releaseDate;

  private final int popularity;

  private final int voteAverage;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public TMDBMovie(
      @JsonProperty("id") int id,
      @JsonProperty("original_title") String originalTitle,
      @JsonProperty("original_language") String originalLanguage,
      @JsonProperty("title") String title,
      @JsonProperty("overview") String overview,
      @JsonProperty("release_date") LocalDate releaseDate,
      @JsonProperty("popularity") int popularity,
      @JsonProperty("vote_average") int voteAverage) {
    this.id = id;
    this.originalTitle = originalTitle;
    this.originalLanguage = originalLanguage;
    this.title = title;
    this.overview = overview;
    this.releaseDate = releaseDate;
    this.popularity = popularity;
    this.voteAverage = voteAverage;
  }

  public int getId() {
    return id;
  }

  public String getOriginalTitle() {
    return originalTitle;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public String getTitle() {
    return title;
  }

  public String getOverview() {
    return overview;
  }

  public int getPopularity() {
    return popularity;
  }

  public int getVoteAverage() {
    return voteAverage;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }
}

package com.octo.ajava.fixture;

import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.time.LocalDate;

public class TMDBMovieTestBuilder {

  private int id = 502356;
  private String originalTitle = "The Super Mario Bros. Movie";
  private String originalLanguage = "en";
  private String title = "The Super Mario Bros. Movie";
  private String overview =
      "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.";
  private LocalDate releaseDate = LocalDate.of(2023, 4, 5);
  private int popularity = 9513;
  private int voteAverage = 7;

  public static TMDBMovieTestBuilder unTMDBMovie() {
    return new TMDBMovieTestBuilder();
  }

  public TMDBMovie build() {
    return new TMDBMovie(
        id, originalTitle, originalLanguage, title, overview, releaseDate, popularity, voteAverage);
  }
}

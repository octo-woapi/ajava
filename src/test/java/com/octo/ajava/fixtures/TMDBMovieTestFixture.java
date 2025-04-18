package com.octo.ajava.fixtures;

import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.time.LocalDate;
import java.util.List;

public class TMDBMovieTestFixture {

  private int id;
  private String originalTitle;
  private String originalLanguage;
  private String title;
  private String overview;
  private LocalDate releaseDate;
  private int popularity;
  private int voteAverage;

  public static TMDBMovieTestFixture unTMDBMovie() {
    return new TMDBMovieTestFixture();
  }

  public TMDBMovieTestFixture avecId(int id) {
    this.id = id;
    return this;
  }

  public TMDBMovieTestFixture avecTitle(String title) {
    this.title = title;
    return this;
  }

  public TMDBMovie build() {
    return new TMDBMovie(
        id, originalTitle, originalLanguage, title, overview, releaseDate, popularity, voteAverage);
  }

  public static List<TMDBMovie> deuxFilmsPopulairesVenantDeTMTB() {
    return List.of(
        new TMDBMovie(
            502356,
            "The Super Mario Bros. Movie",
            "en",
            "The Super Mario Bros. Movie",
            "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
            LocalDate.of(2023, 4, 5),
            8501,
            8),
        new TMDBMovie(
            76600,
            "Avatar: The Way of Water",
            "en",
            "Avatar: The Way of Water",
            "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
            LocalDate.of(2022, 12, 14),
            1453,
            8));
  }

  public static List<TMDBMovie> deuxFilmsRecherchesVenantDeTMTB() {
    return List.of(
        new TMDBMovie(
            414906,
            "The Batman",
            "en",
            "The Batman",
            "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.",
            LocalDate.of(2022, 3, 1),
            153,
            8),
        new TMDBMovie(
            272,
            "Batman Begins",
            "en",
            "Batman Begins",
            "Driven by tragedy, billionaire Bruce Wayne dedicates his life to uncovering and defeating the corruption that plagues his home, Gotham City.  Unable to work within the system, he instead creates a new identity, a symbol of fear for the criminal underworld - The Batman.",
            LocalDate.of(2005, 6, 10),
            42,
            8));
  }
}

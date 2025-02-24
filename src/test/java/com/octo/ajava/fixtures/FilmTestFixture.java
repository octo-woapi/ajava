package com.octo.ajava.fixtures;

import static java.util.Collections.EMPTY_LIST;

import com.octo.ajava.domain.Film;
import java.time.LocalDate;
import java.util.List;

public class FilmTestFixture {

  private int id;
  private String titre;
  private String synopsis;
  private List<String> genres = EMPTY_LIST;
  private LocalDate dateDeSortie;

  public static FilmTestFixture unFilm() {
    return new FilmTestFixture();
  }

  public static List<Film> deuxFilmsPopulaires() {
    return List.of(
        new Film(
            502356,
            "The Super Mario Bros. Movie",
            "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
            EMPTY_LIST,
            LocalDate.of(2023, 4, 5)),
        new Film(
            76600,
            "Avatar: The Way of Water",
            "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
            EMPTY_LIST,
            LocalDate.of(2022, 12, 14)));
  }

  public static List<Film> deuxFilmsRecherches() {
    return List.of(
        new Film(
            414906,
            "The Batman",
            "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.",
            EMPTY_LIST,
            LocalDate.of(2022, 3, 1)),
        new Film(
            272,
            "Batman Begins",
            "Driven by tragedy, billionaire Bruce Wayne dedicates his life to uncovering and defeating the corruption that plagues his home, Gotham City.  Unable to work within the system, he instead creates a new identity, a symbol of fear for the criminal underworld - The Batman.",
            EMPTY_LIST,
            LocalDate.of(2005, 6, 10)));
  }

  public FilmTestFixture avecTitre(String titre) {
    this.titre = titre;
    return this;
  }

  public Film build() {
    return new Film(id, titre, synopsis, genres, dateDeSortie);
  }
}

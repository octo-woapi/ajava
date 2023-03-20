package com.octo.ajava.fixture;

import com.octo.ajava.domain.Film;
import java.time.LocalDate;
import java.util.List;

public class FilmFixture {

  public static List<Film> uneListeDeFilms() {
    return List.of(
        new Film(
            1,
            "Pulp Fiction",
            "Les vies de deux hommes de main...",
            List.of("Policier", "Drame"),
            LocalDate.of(1994, 9, 23)),
        new Film(
            2,
            "Les Dents de la Mer",
            "Un shérif local, un biologiste marin et un vieux marin...",
            List.of("Aventure", "Thriller"),
            LocalDate.of(1975, 6, 20)));
  }

  public static List<Film> deuxFilmsProvenantDeTMDB() {
    return List.of(
        new Film(
            297761,
            "Suicide Squad",
            "From DC Comics comes the Suicide Squad...",
            List.of(),
            LocalDate.of(2016, 8, 3)),
        new Film(
            324668,
            "Jason Bourne",
            "The most dangerous former operative of the CIA...",
            List.of(),
            LocalDate.of(2016, 7, 27)));
  }

  public static List<Film> deuxFilmsRecherchesProvenantDeTMDB() {
    return List.of(
        new Film(
            414906,
            "The Batman",
            "In his second year of fighting crime, Batman...",
            List.of(),
            LocalDate.of(2022, 3, 1)),
        new Film(
            272,
            "Batman Begins",
            "Driven by tragedy, billionaire Bruce Wayne dedicates his life to uncovering and defeating the corruption...",
            List.of(),
            LocalDate.of(2005, 6, 23)));
  }
}

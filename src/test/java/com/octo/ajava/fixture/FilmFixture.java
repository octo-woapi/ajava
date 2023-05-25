package com.octo.ajava.fixture;

import com.octo.ajava.domain.Film;
import java.time.LocalDate;
import java.util.List;

public class FilmFixture {

  public static List<Film> uneListeDeFilm() {
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

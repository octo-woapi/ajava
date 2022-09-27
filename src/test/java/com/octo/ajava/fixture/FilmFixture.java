package com.octo.ajava.fixture;

import com.octo.ajava.domain.Film;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class FilmFixture {

  public static List<Film> uneListeDeFilms() {
    return List.of(
        new Film(
            UUID.fromString("5b64010d-8863-496a-b845-89c00a9d6139"),
            "Pulp Fiction",
            "Les vies de deux hommes de main...",
            List.of("Policier", "Drame"),
            LocalDate.of(1994, 9, 23)),
        new Film(
            UUID.fromString("f47e5d10-d862-4859-bd7c-adcc27aa9eea"),
            "Les Dents de la Mer",
            "Un shérif local, un biologiste marin et un vieux marin...",
            List.of("Aventure", "Thriller"),
            LocalDate.of(1975, 6, 20)));
  }
}

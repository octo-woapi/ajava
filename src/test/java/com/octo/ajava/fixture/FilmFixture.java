package com.octo.ajava.fixture;

import static java.util.Collections.emptyList;

import com.octo.ajava.domain.Film;
import java.time.LocalDate;
import java.util.List;

public class FilmFixture {

  public static List<Film> deuxFilmsPopulaires() {
    return List.of(
        new Film(
            502356,
            "The Super Mario Bros. Movie",
            "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
            emptyList(),
            LocalDate.of(2023, 4, 5)),
        new Film(
            76600,
            "Avatar: The Way of Water",
            "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
            emptyList(),
            LocalDate.of(2022, 12, 14)));
  }
}

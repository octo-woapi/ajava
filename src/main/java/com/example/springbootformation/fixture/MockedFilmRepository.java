package com.example.springbootformation.fixture;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.repositories.FilmRepository;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record MockedFilmRepository() implements FilmRepository {
  @Override
  public List<Film> recupererLesFilms() throws Exception {
    return List.of(
        new Film(
            UUID.fromString("43be220c-05b0-4fb2-9a49-aeeb3306a646"),
            "Spirited Away",
            "Spirited Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her parents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents have been changed into pigs along with others inside a bathhouse full of these creatures. Will she ever see the world how it once was?",
            List.of(),
            new Date()));
  }

  @Override
  public List<Film> recupererLesFilmsAvecPagination() throws Exception {
    return null;
  }

  @Override
  public List<Film> chercherDesFilms() throws Exception {
    return null;
  }
}

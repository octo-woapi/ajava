package com.octo.ajava.fixture;

import static java.util.Collections.emptyList;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.time.LocalDate;
import java.util.List;

public class FilmTestBuilder {

  private int id;
  private String titre;
  private String synopsis;
  private List<String> genres;
  private LocalDate dateDeSortie;

  public static FilmTestBuilder unFilm() {
    return new FilmTestBuilder();
  }

  public FilmTestBuilder avecTMDBMovie(TMDBMovie tmdbMovie) {
    id = tmdbMovie.getId();
    titre = tmdbMovie.getTitle();
    synopsis = tmdbMovie.getOverview();
    genres = emptyList();
    dateDeSortie = tmdbMovie.getReleaseDate();
    return this;
  }

  public Film build() {
    return new Film(id, titre, synopsis, genres, dateDeSortie);
  }
}

package com.octo.ajava.infra.mapper;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TMDBFilmMapper {

  public List<Film> convertirEnFilms(List<TMDBMovie> tmdbMovies) {
    return tmdbMovies.stream().map(this::convertirEnFilm).toList();
  }

  public Film convertirEnFilm(TMDBMovie tmdbMovie) {
    return new Film(
        tmdbMovie.getId(),
        tmdbMovie.getTitle(),
        tmdbMovie.getOverview(),
        Collections.emptyList(),
        tmdbMovie.getReleaseDate());
  }
}

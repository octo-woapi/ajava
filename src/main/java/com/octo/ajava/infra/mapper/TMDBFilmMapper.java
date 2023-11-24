package com.octo.ajava.infra.mapper;

import static java.util.Collections.emptyList;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TMDBFilmMapper {

  public List<Film> convertirEnFilms(PaginatedTMDBMovies paginatedTMDBMovies) {
    var movies = paginatedTMDBMovies.getMovies();

    if (movies == null) {
      return emptyList();
    }

    return movies.stream().map(TMDBFilmMapper::convertirEnFilm).toList();
  }

  private static Film convertirEnFilm(TMDBMovie tmdbMovie) {
    return new Film(
            tmdbMovie.getId(),
            tmdbMovie.getTitle(),
            tmdbMovie.getOverview(),
            emptyList(),
            tmdbMovie.getReleaseDate());
  }
}

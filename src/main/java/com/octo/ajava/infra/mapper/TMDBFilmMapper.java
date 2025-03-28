package com.octo.ajava.infra.mapper;

import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.emptyList;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TMDBFilmMapper {

  public List<Film> convertirEnFilms(PaginatedTMDBMovies paginatedTMDBMovies) {
    List<TMDBMovie> movies = paginatedTMDBMovies.getMovies();

    if (movies == null) {
      return emptyList();
    }

    return movies.stream()
        .map(
            tmdbMovie ->
                new Film(
                    tmdbMovie.getId(),
                    tmdbMovie.getTitle(),
                    tmdbMovie.getOverview(),
                    EMPTY_LIST,
                    tmdbMovie.getReleaseDate()))
        .toList();
  }
}

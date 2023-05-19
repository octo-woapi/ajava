package com.octo.ajava.infra.mapper;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import org.springframework.stereotype.Component;

@Component
public class TMDBFilmMapper {

  public List<Film> convertirEnFilms(PaginatedTMDBMovies paginatedTMDBMovies) {
    var movies = paginatedTMDBMovies.getMovies();

    if (movies == null) {
      return emptyList();
    }

    return movies.stream().map(tmdbMovie -> new Film(
            tmdbMovie.getId(),
            tmdbMovie.getTitle(),
            tmdbMovie.getOverview(),
            emptyList(),
            tmdbMovie.getReleaseDate()
    )).toList();
  }
}

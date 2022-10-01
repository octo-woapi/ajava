package com.octo.ajava.infra.mapper;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.client.resource.PaginatedTMDBMovies;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TMDBFilmMapper {

  public List<Film> toFilm(PaginatedTMDBMovies tmdbMovies) {
    return tmdbMovies.getMovies().stream()
        .map(
            tmdbMovie ->
                new Film(
                    tmdbMovie.getId(),
                    tmdbMovie.getTitle(),
                    tmdbMovie.getOverview(),
                    Collections.emptyList(),
                    tmdbMovie.getReleaseDate()))
        .toList();
  }
}

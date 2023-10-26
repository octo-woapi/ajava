package com.octo.ajava.infra.mapper;

import static java.util.Collections.emptyList;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TMDBFilmMapper {

  public List<Film> convertirEnFilms(PaginatedTMDBMovies paginatedTMDBMovies) {
    List<TMDBMovie> movies = paginatedTMDBMovies.getMovies();

    if (movies == null) {
      return emptyList();
    }

    List<Film> list = new ArrayList<>();
    for (TMDBMovie movie : movies) {
      Film film =
          new Film(
              movie.getId(),
              movie.getTitle(),
              movie.getOverview(),
              emptyList(),
              movie.getReleaseDate());
      list.add(film);
    }
    return list;
  }
}

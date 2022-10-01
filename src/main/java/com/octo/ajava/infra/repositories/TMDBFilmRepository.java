package com.octo.ajava.infra.repositories;

import static java.util.Collections.emptyList;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import com.octo.ajava.infra.client.TMDBHttpClient;
import com.octo.ajava.infra.client.resource.PaginatedTMDBMovies;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "film.source", havingValue = "TMDB")
public class TMDBFilmRepository implements FilmRepository {

  private final TMDBHttpClient tmdbHttpClient;

  private final TMDBFilmMapper tmdbFilmMapper;

  public TMDBFilmRepository(TMDBHttpClient tmdbHttpClient, TMDBFilmMapper tmdbFilmMapper) {
    this.tmdbHttpClient = tmdbHttpClient;
    this.tmdbFilmMapper = tmdbFilmMapper;
  }

  @Override
  public List<Film> recupererLesFilms() throws Exception {
    var tmdbResponse =
        this.tmdbHttpClient.get().getForEntity("/movie/popular", PaginatedTMDBMovies.class);
    var tmdbMovies = tmdbResponse.getBody();
    if (tmdbMovies == null) return emptyList();
    return this.tmdbFilmMapper.toFilm(tmdbMovies);
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

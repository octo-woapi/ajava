package com.octo.ajava.infra.repositories;

import static java.util.Collections.EMPTY_LIST;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
import java.util.List;
import java.util.Optional;
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
  public List<Film> recupererLesFilms() {
    PaginatedTMDBMovies paginatedTMDBMovies = tmdbHttpClient.recupererLesFilmsPopulaires();
    return tmdbFilmMapper.convertirEnFilms(paginatedTMDBMovies);
  }

  @Override
  public List<Film> chercherDesFilms(String query) {
    PaginatedTMDBMovies paginatedTMDBMovies = tmdbHttpClient.chercherDesFilms(query);
    return tmdbFilmMapper.convertirEnFilms(paginatedTMDBMovies);
  }

  @Override
  public Optional<Film> chercherUnFilmParId(int id) {
    Optional<TMDBMovie> oTMDBMovie = tmdbHttpClient.chercherUnFilmParId(id);

    if (oTMDBMovie.isPresent()) {
      TMDBMovie tmdbMovie = oTMDBMovie.get();
      return Optional.of(
          new Film(
              tmdbMovie.getId(),
              tmdbMovie.getTitle(),
              tmdbMovie.getOverview(),
              EMPTY_LIST,
              tmdbMovie.getReleaseDate()));
    }
    return Optional.empty();
  }
}

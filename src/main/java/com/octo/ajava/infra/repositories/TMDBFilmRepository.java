package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.api_client.entities.TMDBMovie;
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
  public List<Film> recupererLesFilms() {
    List<TMDBMovie> tmdbMovies = tmdbHttpClient.recupererLesFilmsPopulaires();
    return tmdbFilmMapper.convertirEnFilms(tmdbMovies);
  }

  @Override
  public List<Film> chercherDesFilms(String query) {
    List<TMDBMovie> tmdbMovies = tmdbHttpClient.chercherDesFilms(query);
    return tmdbFilmMapper.convertirEnFilms(tmdbMovies);
  }
}

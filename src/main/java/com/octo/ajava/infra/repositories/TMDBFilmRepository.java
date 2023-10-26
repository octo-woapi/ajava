package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.Film;
import com.octo.ajava.infra.api_client.TMDBHttpClient;
import com.octo.ajava.infra.mapper.TMDBFilmMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TMDBFilmRepository {

  private final TMDBHttpClient tmdbHttpClient;
  private final TMDBFilmMapper tmdbFilmMapper;

  public TMDBFilmRepository(TMDBHttpClient tmdbHttpClient, TMDBFilmMapper tmdbFilmMapper) {
    this.tmdbHttpClient = tmdbHttpClient;
    this.tmdbFilmMapper = tmdbFilmMapper;
  }

  public List<Film> recupererLesFilms() {
    // utiliser tmdbHttpClient.recupererLesFilmsPopulaires();
    return null;
  }
}

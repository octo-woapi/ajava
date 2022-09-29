package com.octo.ajava.infra.repositories;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "film.source", havingValue = "IN_MEMORY")
public class InMemoryFilmRepository implements FilmRepository {
  @Override
  public List<Film> recupererLesFilms() throws Exception {
    return null;
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

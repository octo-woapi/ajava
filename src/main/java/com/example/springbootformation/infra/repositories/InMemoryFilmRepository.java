package com.example.springbootformation.infra.repositories;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.repositories.FilmRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
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

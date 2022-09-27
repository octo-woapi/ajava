package com.octo.ajava.domain.usecases;

import com.octo.ajava.domain.Film;
import com.octo.ajava.domain.repositories.FilmRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RecupererLesFilmsAvecPaginationUseCase {

  private final FilmRepository filmRepository;

  RecupererLesFilmsAvecPaginationUseCase(FilmRepository filmRepository) {
    this.filmRepository = filmRepository;
  }

  public List<Film> executer() throws Exception {
    return this.filmRepository.recupererLesFilmsAvecPagination();
  }
}

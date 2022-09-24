package com.example.springbootformation.domain.usecases;

import com.example.springbootformation.domain.Film;
import com.example.springbootformation.domain.repositories.FilmRepository;
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

package com.octo.ajava.domain.repositories;

import com.octo.ajava.domain.Film;
import java.util.List;

public interface FilmRepository {
  List<Film> recupererLesFilms() throws Exception;

  List<Film> recupererLesFilmsAvecPagination() throws Exception;

  List<Film> chercherDesFilms() throws Exception;
}
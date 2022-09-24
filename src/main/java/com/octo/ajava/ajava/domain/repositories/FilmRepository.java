package com.octo.ajava.ajava.domain.repositories;

import com.octo.ajava.ajava.domain.Film;

import java.util.List;

public interface FilmRepository {
    List<Film> recupererLesFilms() throws Exception;
    List<Film> recupererLesFilmsAvecPagination() throws Exception;
    List<Film> chercherDesFilms() throws Exception;

}

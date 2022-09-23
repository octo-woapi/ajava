package com.example.springbootformation.domain.repositories;

import com.example.springbootformation.domain.Film;

import java.util.List;

public interface FilmRepository {
    List<Film> recupererLesFilms() throws Exception;
    List<Film> recupererLesFilmsAvecPagination() throws Exception;
    List<Film> chercherDesFilms() throws Exception;

}
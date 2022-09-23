package com.example.springbootformation.domain.repositories;

import com.example.springbootformation.domain.FilmVu;

import java.util.List;

public interface FilmVuRepository {
    FilmVu ajouterUnFilmVu() throws Exception;
    List<FilmVu> recupererMesFilmsVus() throws Exception;
    void supprimerUnFilmVu() throws Exception;
}
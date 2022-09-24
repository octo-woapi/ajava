package com.octo.ajava.ajava.domain.repositories;

import com.octo.ajava.ajava.domain.FilmVu;

import java.util.List;

public interface FilmVuRepository {
    FilmVu ajouterUnFilmVu() throws Exception;
    List<FilmVu> recupererMesFilmsVus() throws Exception;
    void supprimerUnFilmVu() throws Exception;
}

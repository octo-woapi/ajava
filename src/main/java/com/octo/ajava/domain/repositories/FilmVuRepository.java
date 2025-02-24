package com.octo.ajava.domain.repositories;

import com.octo.ajava.domain.FilmVu;

public interface FilmVuRepository {

  FilmVu chercherUnFilmVu(int filmId, String utilisateurId) throws Exception;

  FilmVu ajouterUnFilmVu(FilmVu filmVu) throws Exception;
}

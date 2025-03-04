package com.octo.ajava.domain.repositories;

import com.octo.ajava.domain.FilmVu;
import java.util.List;

public interface FilmVuRepository {

  FilmVu chercherUnFilmVu(int filmId, String utilisateurId) throws Exception;

  FilmVu ajouterUnFilmVu(FilmVu filmVu) throws Exception;

  FilmVu modifierUnFilmVu(FilmVu filmVu) throws Exception;

  List<FilmVu> recupererMesFilmsVus(String userId) throws Exception;
}

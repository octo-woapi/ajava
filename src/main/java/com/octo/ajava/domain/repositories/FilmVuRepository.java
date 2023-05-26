package com.octo.ajava.domain.repositories;

import com.octo.ajava.domain.FilmVu;
import java.util.List;

public interface FilmVuRepository {
  FilmVu ajouterUnFilmVu(FilmVu filmVu) throws Exception;

  List<FilmVu> recupererMesFilmsVus(String userId) throws Exception;
}

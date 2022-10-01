package com.octo.ajava.fixture;

import com.octo.ajava.domain.FilmVu;
import java.util.List;

public class FilmVuFixture {

  public static List<FilmVu> uneListeDeFilmVus() {
    return List.of(
        new FilmVu(1, "Basic dXNlcjpwYXNzd29yZA==", "10/10", "Batman c'est ouf"),
        new FilmVu(2, "Basic dXNlcjpwYXNzd29yZA==", "1/10", "Star nul"));
  }
}

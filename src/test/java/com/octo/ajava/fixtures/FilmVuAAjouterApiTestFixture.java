package com.octo.ajava.fixtures;

import com.octo.ajava.infra.controllers.entities.FilmVuAAjouterApi;

public class FilmVuAAjouterApiTestFixture {

  private int filmId;
  private String note;
  private String commentaire;

  public static FilmVuAAjouterApiTestFixture unFilmVuAAjouterApi() {
    return new FilmVuAAjouterApiTestFixture();
  }

  public FilmVuAAjouterApiTestFixture avecFilmId(int filmId) {
    this.filmId = filmId;
    return this;
  }

  public FilmVuAAjouterApiTestFixture avecNote(String note) {
    this.note = note;
    return this;
  }

  public FilmVuAAjouterApiTestFixture avecCommentaire(String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  public FilmVuAAjouterApi build() {
    return new FilmVuAAjouterApi(filmId, note, commentaire);
  }
}

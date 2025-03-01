package com.octo.ajava.fixtures;

import com.octo.ajava.domain.FilmVu;

public class FilmVuTestFixture {

  private int filmId;
  private String utilisateurId;
  private String note;
  private String commentaire;

  public static FilmVuTestFixture unFilmVu() {
    return new FilmVuTestFixture();
  }

  public FilmVuTestFixture avecFilmId(int filmId) {
    this.filmId = filmId;
    return this;
  }

  public FilmVuTestFixture avecUtilisateurId(String utilisateurId) {
    this.utilisateurId = utilisateurId;
    return this;
  }

  public FilmVuTestFixture avecNote(String note) {
    this.note = note;
    return this;
  }

  public FilmVuTestFixture avecCommentaire(String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  public FilmVu build() {
    return new FilmVu(filmId, utilisateurId, note, commentaire);
  }
}

package com.octo.ajava.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "film_vu")
public class FilmVu {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int filmId;
  private String utilisateurId;
  private String note;
  private String commentaire;

  public FilmVu() {}

  public FilmVu(int filmId, String utilisateurId, String note, String commentaire) {
    this.filmId = filmId;
    this.utilisateurId = utilisateurId;
    this.note = note;
    this.commentaire = commentaire;
  }

  public Long getId() {
    return id;
  }

  public int getFilmId() {
    return filmId;
  }

  public String getUtilisateurId() {
    return utilisateurId;
  }

  public String getNote() {
    return note;
  }

  public String getCommentaire() {
    return commentaire;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FilmVu filmVu = (FilmVu) o;
    return filmId == filmVu.filmId
        && Objects.equals(utilisateurId, filmVu.utilisateurId)
        && Objects.equals(note, filmVu.note)
        && Objects.equals(commentaire, filmVu.commentaire);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filmId, utilisateurId, note, commentaire);
  }
}

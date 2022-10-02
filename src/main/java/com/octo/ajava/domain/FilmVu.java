package com.octo.ajava.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

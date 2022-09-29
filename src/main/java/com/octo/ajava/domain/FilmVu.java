package com.octo.ajava.domain;

import javax.persistence.*;

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

    public FilmVu() {
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

    public FilmVu(int filmId, String utilisateurId, String note, String commentaire) {
        this.filmId = filmId;
        this.utilisateurId = utilisateurId;
        this.note = note;
        this.commentaire = commentaire;
    }
}

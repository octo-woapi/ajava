package com.octo.ajava.fixtures;

import com.octo.ajava.infra.controllers.entities.CritiqueApi;

public class CritiqueApiTestFixture {

  private String note;
  private String commentaire;

  public static CritiqueApiTestFixture uneCritiqueApi() {
    return new CritiqueApiTestFixture();
  }

  public CritiqueApiTestFixture avecNote(String note) {
    this.note = note;
    return this;
  }

  public CritiqueApiTestFixture avecCommentaire(String commentaire) {
    this.commentaire = commentaire;
    return this;
  }

  public CritiqueApi build() {
    return new CritiqueApi(note, commentaire);
  }
}

package com.octo.ajava.domain;

import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nom")
  private String nom;

  @Column(name = "prenom")
  private String prénom;

  @Column(name = "password")
  private String motDePasse;
}

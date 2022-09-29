package com.octo.ajava.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Utilisateur {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prénom;
    private String motDePasse;
}

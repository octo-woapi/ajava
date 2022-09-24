package com.octo.ajava.ajava.domain;

import java.util.UUID;

public record Utilisateur(
    UUID id,
    String nom,
    String prénom,
    String motDePasse
) {
}

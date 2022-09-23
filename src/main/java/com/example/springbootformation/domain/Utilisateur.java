package com.example.springbootformation.domain;

import java.util.UUID;

public record Utilisateur(UUID id, String nom, String prenom) {
}

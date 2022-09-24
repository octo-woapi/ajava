package com.example.springbootformation.domain;

import java.util.UUID;

public record FilmVu(UUID utilisateurId, UUID filmId, Float note, String commentaire) {}

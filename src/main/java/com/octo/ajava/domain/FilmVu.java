package com.octo.ajava.domain;

import java.util.UUID;

public record FilmVu(UUID id, UUID idUtilisateur, String note, String commentaire) {}

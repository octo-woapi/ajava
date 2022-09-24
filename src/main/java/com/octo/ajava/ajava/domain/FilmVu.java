package com.octo.ajava.ajava.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FilmVu(
    UUID id,
    UUID idUtilisateur,
    String note,
    String commentaire
) {
}

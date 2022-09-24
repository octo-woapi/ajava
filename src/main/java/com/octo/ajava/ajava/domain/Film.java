package com.octo.ajava.ajava.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record Film(
    UUID id,
    String description,
    String synopsis,
    List<String> genres,
    LocalDate dateDeSortie
) {
}

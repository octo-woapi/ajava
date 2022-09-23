package com.example.springbootformation.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record Film(
        UUID id,
        String nom,
        String synopsis,
        List<String> genres,
        Date dateDeSortie
) {}

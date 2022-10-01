package com.octo.ajava.domain;

import java.time.LocalDate;
import java.util.List;

public record Film(
    int id, String titre, String synopsis, List<String> genres, LocalDate dateDeSortie) {}

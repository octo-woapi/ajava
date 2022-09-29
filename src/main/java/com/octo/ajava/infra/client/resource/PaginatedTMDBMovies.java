package com.octo.ajava.infra.client.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Utilisation des annotations de Jackson :
 *  @JsonIgnoreProperties(ignoreUnknown = true) = ne pas échouer si une propriété n'existe pas en JAVA
 *  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) = à mettre pour que Jackson utilise le constructeur non vide
 *  @JsonProperty("total_results") = mapping entre le nom en JSON et le champ en JAVA
 *
 *  Interet du constructeur ? Avoir des classes immuables.
 *  Interet des annotations des propriétés JSON ? Découpler le naming JSON et le naming JAVA
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginatedTMDBMovies {

    private final int page;

    private final List<TMDBMovie> movies;

    private final int totalResults;

    private final int totalPages;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PaginatedTMDBMovies(
            @JsonProperty("page") int page,
            @JsonProperty("results") List<TMDBMovie> movies,
            @JsonProperty("total_results") int totalResults,
            @JsonProperty("total_pages") int totalPages) {
        this.page = page;
        this.movies = movies;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public List<TMDBMovie> getMovies() {
        return movies;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

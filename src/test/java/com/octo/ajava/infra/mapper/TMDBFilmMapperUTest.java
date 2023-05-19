package com.octo.ajava.infra.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.fixture.FilmFixture;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import static java.util.Collections.emptyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TMDBFilmMapperUTest {

    private TMDBFilmMapper tmdbFilmMapper = new TMDBFilmMapper();

    @Test
    void quand_il_n_y_a_pas_de_film_dans_la_response_retourne_une_liste_vide() throws JsonProcessingException {
        // GIVEN
        var jsonResponse = """
                        {
                             "page": 1,
                             "results": [],
                             "total_pages": 1,
                             "total_results": 0
                         }
                """;
        var responseBody = ObjectMapperBuilder.handle().readValue(jsonResponse, PaginatedTMDBMovies.class);


        // WHEN
        var result = tmdbFilmMapper.convertirEnFilms(responseBody);

        // THEN
        Assertions.assertEquals(emptyList(), result);
    }

    @Test
    void devrait_renvoyer_un_Film() throws JsonProcessingException {
        // GIVEN
        var jsonResponse = """
                        {
                          "page": 1,
                          "results": [
                            {
                                "adult": false,
                                "backdrop_path": "/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg",
                                "genre_ids": [
                                    80,
                                    9648,
                                    53
                                ],
                                "id": 414906,
                                "original_language": "en",
                                "original_title": "The Batman",
                                "overview": "In his second year of fighting crime, Batman uncovers corruption in Gotham City that connects to his own family while facing a serial killer known as the Riddler.",
                                "popularity": 153.181,
                                "poster_path": "/74xTEgt7R36Fpooo50r9T25onhq.jpg",
                                "release_date": "2022-03-01",
                                "title": "The Batman",
                                "video": false,
                                "vote_average": 7.71,
                                "vote_count": 7939
                            },
                            {
                               "adult": false,
                               "backdrop_path": "/ew5FcYiRhTYNJAkxoVPMNlCOdVn.jpg",
                               "genre_ids": [
                                   28,
                                   80,
                                   18
                               ],
                               "id": 272,
                               "original_language": "en",
                               "original_title": "Batman Begins",
                               "overview": "Driven by tragedy, billionaire Bruce Wayne dedicates his life to uncovering and defeating the corruption that plagues his home, Gotham City.  Unable to work within the system, he instead creates a new identity, a symbol of fear for the criminal underworld - The Batman.",
                               "popularity": 41.535,
                               "poster_path": "/4MpN4kIEqUjW8OPtOQJXlTdHiJV.jpg",
                               "release_date": "2005-06-10",
                               "title": "Batman Begins",
                               "video": false,
                               "vote_average": 7.695,
                               "vote_count": 19019
                           }
                          ],
                          "total_pages": 8,
                          "total_results": 146
                        }
                """;
        var responseBody = ObjectMapperBuilder.handle().readValue(jsonResponse, PaginatedTMDBMovies.class);

        // WHEN
        var result = tmdbFilmMapper.convertirEnFilms(responseBody);

        // THEN
        Assertions.assertEquals(FilmFixture.deuxFilmsRecherches(), result);
    }
}

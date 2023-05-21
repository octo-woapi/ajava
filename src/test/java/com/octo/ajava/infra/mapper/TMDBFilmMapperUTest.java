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
                           "backdrop_path": "/nLBRD7UPR6GjmWQp6ASAfCTaWKX.jpg",
                           "genre_ids": [
                               16,
                               10751,
                               12,
                               14,
                               35
                           ],
                           "id": 502356,
                           "original_language": "en",
                           "original_title": "The Super Mario Bros. Movie",
                           "overview": "While working underground to fix a water main, Brooklyn plumbers—and brothers—Mario and Luigi are transported down a mysterious pipe and wander into a magical new world. But when the brothers are separated, Mario embarks on an epic quest to find Luigi.",
                           "popularity": 10702.084,
                           "poster_path": "/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg",
                           "release_date": "2023-04-05",
                           "title": "The Super Mario Bros. Movie",
                           "video": false,
                           "vote_average": 7.7,
                           "vote_count": 2875
                       },
                        {
                          "adult": false,
                          "backdrop_path": "/8rpDcsfLJypbO6vREc0547VKqEv.jpg",
                          "genre_ids": [
                              878,
                              12,
                              28
                          ],
                          "id": 76600,
                          "original_language": "en",
                          "original_title": "Avatar: The Way of Water",
                          "overview": "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
                          "popularity": 1879.806,
                          "poster_path": "/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                          "release_date": "2022-12-14",
                          "title": "Avatar: The Way of Water",
                          "video": false,
                          "vote_average": 7.7,
                          "vote_count": 8070
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
    Assertions.assertEquals(FilmFixture.deuxFilmsPopulaires(), result);
  }
}

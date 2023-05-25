package com.octo.ajava.infra.mapper;

import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.infra.api_client.entities.PaginatedTMDBMovies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TMDBFilmMapperUTest {

    @InjectMocks
    private TMDBFilmMapper tmdbFilmMapper;

    @Test
    void test() {
        // GIVEN
        var jsonResponse = """
                // mettre le json que je veux tester ici
                """;
        var responseBody = ObjectMapperBuilder.handle().readValue(jsonResponse, PaginatedTMDBMovies.class);
        var expected = // se servir de la FilmFixture si vous voulez

        // WHEN
        var result = tmdbFilmMapper.methodeQueJeVeuxTester(responseBody);

        // THEN
        Assertions.assertEquals(expected, result);
    }

}

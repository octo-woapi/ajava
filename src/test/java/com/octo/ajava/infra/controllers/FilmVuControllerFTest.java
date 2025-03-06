package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.FilmVu;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = AjavaApplication.class)
class FilmVuControllerFTest {

  @Test
  void ajouterFilmVu_devrait_renvoyer_201_avec_le_film_vu() throws Exception {
    // Given

    // When
    var response =
        RestAssured.given()
            .contentType("application/json")
            .auth()
            .basic("user", "password")
            .body(
                """
                    {
                      "filmId": 1,
                      "note": "10/10",
                      "commentaire": "Batman c'est ouf"
                    }
                    """)
            .post("/api/films_vus")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .response()
            .asString();

    // Then
    FilmVu filmAjoute = ObjectMapperBuilder.handle().readValue(response, FilmVu.class);

    assertThat(filmAjoute.getFilmId()).isEqualTo(1);
  }

  @Test
  void doit_renvoyer_le_code_http_200_quand_lister_des_films_a_renvoye_des_resultats()
      throws Exception {
    // Given

    // When
    var response =
        RestAssured.given()
            .contentType("application/json")
            .auth()
            .basic("user", "password")
            .get("/api/films_vus")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    // Then
    FilmVu[] listeDeFilms = ObjectMapperBuilder.handle().readValue(response, FilmVu[].class);

    assertThat(listeDeFilms.length).isNotNegative();
  }
}

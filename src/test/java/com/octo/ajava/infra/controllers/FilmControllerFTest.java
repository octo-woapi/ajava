package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.Film;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    classes = AjavaApplication.class)
class FilmControllerFTest {

  @Test
  void recuperTousLesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film() throws Exception {
    // Given

    // When
    var response =
        RestAssured.given()
            .get("/api/films")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    // Then
    Film[] listeDeFilms = ObjectMapperBuilder.handle().readValue(response, Film[].class);

    assertThat(listeDeFilms.length).isEqualTo(22);
  }

  @Test
  void chercherDesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film_recherchee()
      throws Exception {
    // Given

    // When
    var response =
        RestAssured.given()
            .get("/api/films/search?query=totoro")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    // Then
    Film[] listeDeFilms = ObjectMapperBuilder.handle().readValue(response, Film[].class);

    assertThat(listeDeFilms.length).isEqualTo(1);
  }

  @Test
  void chercherUnFilmParId_devrait_renvoyer_une_HTTP_200_avec_le_film_trouve() throws Exception {
    // Given

    // When
    var response =
        RestAssured.given()
            .get("/api/films/1")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    // Then
    Film film = ObjectMapperBuilder.handle().readValue(response, Film.class);

    Assertions.assertNotNull(film);
  }
}

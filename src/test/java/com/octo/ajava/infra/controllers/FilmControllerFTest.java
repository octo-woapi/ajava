package com.octo.ajava.infra.controllers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.Film;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = AjavaApplication.class)
class FilmControllerFTest {

  @LocalServerPort private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void recuperTousLesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film() throws Exception {
    // Given

    // When
    String response =
        given()
            .get("/api/films")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    // Then
    Film[] listeDeFilms = ObjectMapperBuilder.handle().readValue(response, Film[].class);

    assertEquals(22, listeDeFilms.length);
  }
}

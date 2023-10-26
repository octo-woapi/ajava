package com.octo.ajava.infra.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.Film;
import io.restassured.RestAssured;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = AjavaApplication.class)
class FilmControllerFTest {

  @LocalServerPort private int port;

  @PostConstruct
  public void init() {
    RestAssured.baseURI = "http://localhost:" + port;
  }

  @Test
  void recuperTousLesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film() throws Exception {
    // Given

    // When
    String response =
        RestAssured.given()
            .get("la route que je souhaite tester")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response()
            .asString();

    // Then
    Film[] listeDeFilms = ObjectMapperBuilder.handle().readValue(response, Film[].class);

    assertEquals(0, listeDeFilms.length);
  }
}

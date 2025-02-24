package com.octo.ajava.infra.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.Film;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = AjavaApplication.class)
class FilmControllerFTest {

  @LocalServerPort private Integer port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }

  @Test
  void recuperTousLesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film() throws Exception {
    // When
    Response response =
        given().get("/api/films").then().statusCode(OK.value()).extract().response();

    // Then
    Film[] listeDeFilms = ObjectMapperBuilder.handle().readValue(response.asString(), Film[].class);
    assertThat(listeDeFilms.length).isEqualTo(22);
  }

  @Test
  void chercherDesFilms_devrait_renvoyer_une_HTTP_200_et_une_liste_de_film_recherchee()
      throws Exception {
    // When
    Response response =
        given()
            .get("/api/films/search?query=totoro")
            .then()
            .statusCode(OK.value())
            .extract()
            .response();

    // Then
    Film[] listeDeFilms = ObjectMapperBuilder.handle().readValue(response.asString(), Film[].class);
    assertThat(listeDeFilms.length).isEqualTo(1);
  }
}

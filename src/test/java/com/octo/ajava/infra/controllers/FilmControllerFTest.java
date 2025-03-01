package com.octo.ajava.infra.controllers;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.domain.Film;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    Film[] filmsTrouves =
        when()
            .get("/api/films")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(Film[].class);

    // Then
    assertThat(filmsTrouves).hasSize(22);
  }

  @DisplayName("devrait renvoyer la liste des fils trouvés et un HTTP 200")
  @Test
  void chercherDesFilms() throws Exception {
    // When
    Film[] filmsTrouves =
        when()
            .get("/api/films/search?query=totoro")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .as(Film[].class);

    // Then
    assertThat(filmsTrouves).hasSize(1);
  }
}

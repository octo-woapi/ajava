package com.octo.ajava.infra.controllers;

import static io.restassured.RestAssured.when;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.domain.Film;
import io.restassured.RestAssured;
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

  @DisplayName("devrait renvoyer une liste de films et un HTTP 200")
  @Test
  void recuperTousLesFilms() throws Exception {
    // When
    Film[] filmsTrouves =
        when().get("/api/films").then().statusCode(SC_OK).extract().body().as(Film[].class);

    // Then
    assertThat(filmsTrouves).hasSize(23);
  }

  @DisplayName("devrait renvoyer la liste des films trouvés et un HTTP 200")
  @Test
  void chercherDesFilms() throws Exception {
    // Given
    String titreRecherche = "Castle in the Sky";

    // When
    Film[] filmsTrouves =
        when()
            .get("/api/films/search?query={titre}", titreRecherche)
            .then()
            .statusCode(SC_OK)
            .extract()
            .body()
            .as(Film[].class);

    // Then
    assertThat(filmsTrouves).singleElement().extracting("titre").isEqualTo(titreRecherche);
  }

  @Test
  void chercherUnFilmParId_devrait_renvoyer_une_HTTP_200_avec_le_film_trouve() throws Exception {
    // Given
    int idCherche = 1;

    // When
    Film filmTrouve =
        when()
            .get("/api/films/{id}", idCherche)
            .then()
            .statusCode(SC_OK)
            .extract()
            .body()
            .as(Film.class);

    // Then
    assertThat(filmTrouve.id()).isEqualTo(idCherche);
    assertThat(filmTrouve.titre()).isEqualTo("Castle in the Sky");
  }
}

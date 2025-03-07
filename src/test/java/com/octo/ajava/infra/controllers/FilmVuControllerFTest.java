package com.octo.ajava.infra.controllers;

import static com.octo.ajava.fixtures.CritiqueApiTestFixture.uneCritiqueApi;
import static com.octo.ajava.fixtures.FilmVuAAjouterApiTestFixture.unFilmVuAAjouterApi;
import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.testcontainers.utility.MountableFile.forClasspathResource;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.infra.controllers.entities.CritiqueApi;
import com.octo.ajava.infra.controllers.entities.FilmVuAAjouterApi;
import com.octo.ajava.infra.repositories.DatabaseFilmVuDAO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = AjavaApplication.class)
@Testcontainers
class FilmVuControllerFTest {

  private static final String UTILISATEUR_ID = "user";
  private static final String MOT_DE_PASSE = "password";

  @LocalServerPort private Integer port;
  @Autowired private DatabaseFilmVuDAO databaseFilmVuDAO;

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:14-alpine")
          .withCopyFileToContainer(
              forClasspathResource("/docker_postgres_init.sql"), "/docker-entrypoint-initdb.d/");

  @DynamicPropertySource
  static void registerMySQLProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    databaseFilmVuDAO.deleteAll();
  }

  @DisplayName("devrait chercher un FilmVu et le renvoyer avec un HTTP 200")
  @Test
  void chercherUnFilmVu() throws Exception {
    // Given
    int filmId = 25;
    FilmVu filmVuExistant =
        databaseFilmVuDAO.save(
            unFilmVu()
                .avecFilmId(filmId)
                .avecUtilisateurId(UTILISATEUR_ID)
                .avecNote("10/10")
                .avecCommentaire("Shakespeare for ever!")
                .build());

    // When
    FilmVu filmVuTrouve =
        given()
            .auth()
            .basic(UTILISATEUR_ID, MOT_DE_PASSE)
            .when()
            .get("/api/films_vus/{filmId}", filmId)
            .then()
            .statusCode(SC_OK)
            .extract()
            .body()
            .as(FilmVu.class);

    // Then
    assertThat(filmVuTrouve).isEqualTo(filmVuExistant);
  }

  @DisplayName("devrait renvoyer le FilmVu ajouté, et un HTTP 201")
  @Test
  void ajouterUnFilmVu() throws Exception {
    // Given
    FilmVuAAjouterApi filmVuAAjouterApi =
        unFilmVuAAjouterApi()
            .avecFilmId(1)
            .avecNote("10/10")
            .avecCommentaire("Batman c'est ouf")
            .build();

    // When
    FilmVu filmAjoute =
        given()
            .contentType("application/json")
            .auth()
            .basic(UTILISATEUR_ID, MOT_DE_PASSE)
            .body(filmVuAAjouterApi)
            .when()
            .post("/api/films_vus")
            .then()
            .statusCode(SC_CREATED)
            .extract()
            .body()
            .as(FilmVu.class);

    // Then
    assertThat(filmAjoute.getFilmId()).isEqualTo(1);
    assertThat(filmAjoute.getUtilisateurId()).isEqualTo(UTILISATEUR_ID);
    assertThat(filmAjoute.getNote()).isEqualTo("10/10");
    assertThat(filmAjoute.getCommentaire()).isEqualTo("Batman c'est ouf");
  }

  @DisplayName("#modifierFilmVu")
  @Nested
  class ModifierFilmVuTest {

    @DisplayName("devrait renvoyer le FilmVu modifié et un HTTP 200")
    @Test
    void renvoyerFilmVuModifie() throws Exception {
      // Given
      int filmId = 12;
      FilmVu filmVuExistant =
          databaseFilmVuDAO.save(
              unFilmVu()
                  .avecFilmId(filmId)
                  .avecUtilisateurId(UTILISATEUR_ID)
                  .avecNote("05/10")
                  .avecCommentaire("Batman c'est moyen !")
                  .build());

      CritiqueApi critiqueApi =
          uneCritiqueApi()
              .avecNote("10/10")
              .avecCommentaire("Batman finalement c'est génial !")
              .build();

      // When
      FilmVu filmVuModifie =
          given()
              .contentType("application/json")
              .auth()
              .basic(UTILISATEUR_ID, MOT_DE_PASSE)
              .body(critiqueApi)
              .when()
              .put("/api/films_vus/{filmId}", filmId)
              .then()
              .statusCode(SC_OK)
              .extract()
              .response()
              .body()
              .as(FilmVu.class);

      // Then
      assertThat(filmVuModifie.getId()).isEqualTo(filmVuExistant.getId());
      assertThat(filmVuModifie.getFilmId()).isEqualTo(filmId);
      assertThat(filmVuModifie.getUtilisateurId()).isEqualTo(UTILISATEUR_ID);
      assertThat(filmVuModifie.getNote()).isEqualTo("10/10");
      assertThat(filmVuModifie.getCommentaire()).isEqualTo("Batman finalement c'est génial !");
    }

    @DisplayName(
        "devrait retourner une chaine vide et un HTTP 404, si le FilmVu n'existe pas (couple filmId et utilisateurId inexistant")
    @Test
    void retournerHttp404() {
      // Given
      int filmIdErrone = 100;

      databaseFilmVuDAO.save(
          unFilmVu()
              .avecFilmId(12)
              .avecUtilisateurId(UTILISATEUR_ID)
              .avecNote("05/10")
              .avecCommentaire("Batman c'est moyen !")
              .build());

      CritiqueApi critiqueApi =
          uneCritiqueApi()
              .avecNote("10/10")
              .avecCommentaire("Batman finalement c'est génial !")
              .build();

      // When
      Response response =
          given()
              .contentType("application/json")
              .auth()
              .basic(UTILISATEUR_ID, MOT_DE_PASSE)
              .body(critiqueApi)
              .when()
              .put("/api/films_vus/{filmId}", filmIdErrone)
              .then()
              .statusCode(SC_NOT_FOUND)
              .extract()
              .response();

      // Then
      assertThat(response.asString()).isEmpty();
    }
  }

  @DisplayName("devrait renvoyer tous les FilmVu de l'utilisateur connecté")
  @Test
  void renvoyerFilmVusUtilisateur() throws Exception {
    // Given
    databaseFilmVuDAO.saveAll(
        List.of(
            unFilmVu().avecFilmId(50).avecUtilisateurId(UTILISATEUR_ID).build(),
            unFilmVu().avecFilmId(55).avecUtilisateurId(UTILISATEUR_ID).build()));

    // When
    FilmVu[] filmVuTrouves =
        given()
            .contentType("application/json")
            .auth()
            .basic(UTILISATEUR_ID, MOT_DE_PASSE)
            .when()
            .get("/api/films_vus")
            .then()
            .statusCode(SC_OK)
            .extract()
            .body()
            .as(FilmVu[].class);

    // Then
    assertThat(filmVuTrouves)
        .hasSize(2)
        .extracting("filmId", "utilisateurId")
        .contains(tuple(50, UTILISATEUR_ID), tuple(55, UTILISATEUR_ID));
  }
}

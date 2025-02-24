package com.octo.ajava.infra.controllers;

import static com.octo.ajava.fixtures.FilmVuAAjouterApiTestFixture.unFilmVuAAjouterApi;
import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.utility.MountableFile.forClasspathResource;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.infra.controllers.entities.FilmVuAAjouterApi;
import com.octo.ajava.infra.repositories.DatabaseFilmVuDAO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class FilmVuControllerFTest { // TODO

  private static final String UTILISATEUR_ID = "user";
  private static final String MOT_DE_PASSE = "password";

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

  @LocalServerPort private Integer port;
  @Autowired private DatabaseFilmVuDAO databaseFilmVuDAO;

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
            .body("") // TODO
            .when()
            .post("TODO") // TODO
            .then()
            .statusCode(0) // TODO HttpStatus à tester
            .extract()
            .body()
            .as(FilmVu.class);

    // Then
    assertThat("TODO").isEqualTo(1); // TODO
    assertThat("TODO").isEqualTo(UTILISATEUR_ID);
    assertThat("TODO").isEqualTo("10/10");
    assertThat("TODO").isEqualTo("Batman c'est ouf");
  }
}

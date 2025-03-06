package com.octo.ajava.infra.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.testcontainers.utility.MountableFile.forClasspathResource;

import com.octo.ajava.AjavaApplication;
import com.octo.ajava.ObjectMapperBuilder;
import com.octo.ajava.domain.FilmVu;
import com.octo.ajava.infra.repositories.DatabaseFilmVuDAO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
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

  @Test
  void doit_renvoyer_le_code_http_200_quand_lister_des_films_a_renvoye_des_resultats()
      throws Exception {
    // Given

    // When
    var response =
        RestAssured.given()
            .auth()
            .basic("user", "password")
            .header("Content-Type", APPLICATION_JSON)
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

  @Test
  void ajouterFilmVu_devrait_renvoyer_200_avec_le_film_vu() throws Exception {
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
}

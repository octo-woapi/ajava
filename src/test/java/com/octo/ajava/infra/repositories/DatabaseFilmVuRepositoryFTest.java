package com.octo.ajava.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.domain.FilmVu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@SpringBootTest
@Testcontainers
class DatabaseFilmVuRepositoryFTest {

  private final String utilisateurId = "jdurant";

  @Autowired private DatabaseFilmVuDAO databaseFilmVuDAO;
  @Autowired private DatabaseFilmVuRepository databaseFilmVuRepository;

  @Container
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:14-alpine")
          .withCopyFileToContainer(
              MountableFile.forClasspathResource("/docker_postgres_init.sql"),
              "/docker-entrypoint-initdb.d/");

  @DynamicPropertySource
  static void registerMySQLProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
  }

  @BeforeEach
  void setUp() {
    databaseFilmVuDAO.deleteAll();
  }

  @DisplayName("devrait chercher et trouver un FilmVu déjà existant")
  @Test
  void trouverFilmVuExistant() throws Exception {
    // Given
    FilmVu filmVu =
        databaseFilmVuDAO.save(new FilmVu(5, utilisateurId, "10/10", "Batman c'est ouf"));

    // When
    FilmVu filmVuTrouve = databaseFilmVuRepository.chercherUnFilmVu(5, utilisateurId);

    // Then
    assertThat(filmVuTrouve).isEqualTo(filmVu);
  }

  @DisplayName("ne devrait pas trouver un FilmVu qui n'existe pas en BDD")
  @Test
  void chercherUnFilmVuNonExistant() throws Exception {
    // Given
    databaseFilmVuDAO.save(new FilmVu(1, utilisateurId, "10/10", "Batman c'est ouf"));

    // When
    FilmVu filmVuTrouve = databaseFilmVuRepository.chercherUnFilmVu(5, utilisateurId);

    // Then
    assertThat(filmVuTrouve).isNull();
  }

  @DisplayName("devrait ajouter un FilmVu en BDD et le retourner")
  @Test
  void ajouterFilmVu() {
    // Given
    FilmVu filmVu = new FilmVu(1, utilisateurId, "10/10", "Batman c'est ouf");

    // When
    FilmVu filmVuAjoute = databaseFilmVuRepository.ajouterUnFilmVu(filmVu);

    // Then
    assertThat(filmVuAjoute).isEqualTo(filmVu);
  }

  @DisplayName("devrait modifier un FilmVu déjà existant")
  @Test
  void modifierFilmVu() throws Exception {
    // Given
    FilmVu filmVu =
        databaseFilmVuRepository.ajouterUnFilmVu(
            new FilmVu(1, utilisateurId, "10/10", "Batman c'est ouf"));

    // When
    databaseFilmVuRepository.modifierUnFilmVu(null); // TODO

    // Then
    // TODO

  }
}

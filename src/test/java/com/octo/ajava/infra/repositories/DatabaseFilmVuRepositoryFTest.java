package com.octo.ajava.infra.repositories;

import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.utility.MountableFile.forClasspathResource;

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

@SpringBootTest
@Testcontainers
class DatabaseFilmVuRepositoryFTest {

  private static final int FILM_ID = 5;
  private static final String UTILISATEUR_ID = "jdurant";

  private FilmVu filmVuExistant;

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

  @Autowired private DatabaseFilmVuRepository databaseFilmVuRepository;
  @Autowired private DatabaseFilmVuDAO databaseFilmVuDAO;

  @BeforeEach
  void setUp() {
    databaseFilmVuDAO.deleteAll();
    filmVuExistant =
        databaseFilmVuDAO.save(
            unFilmVu()
                .avecFilmId(FILM_ID)
                .avecUtilisateurId(UTILISATEUR_ID)
                .avecNote("10/10")
                .avecCommentaire("Batman c'est ouf")
                .build());
  }

  @DisplayName("devrait chercher et trouver un FilmVu déjà existant")
  @Test
  void trouverFilmVuExistant() throws Exception {
    // When
    FilmVu filmVuTrouve = databaseFilmVuRepository.chercherUnFilmVu(FILM_ID, UTILISATEUR_ID);

    // Then
    assertThat(filmVuTrouve).isEqualTo(filmVuExistant);
  }

  @DisplayName("ne devrait pas trouver un FilmVu qui n'existe pas en BDD")
  @Test
  void chercherUnFilmVuNonExistant() throws Exception {
    // Given
    int filmIdErrone = 10;

    // When
    FilmVu filmVuTrouve = databaseFilmVuRepository.chercherUnFilmVu(filmIdErrone, UTILISATEUR_ID);

    // Then
    assertThat(filmVuTrouve).isNull();
  }

  @DisplayName("devrait ajouter un FilmVu en BDD et le renvoyer")
  @Test
  void ajouterFilmVu() {
    // Given
    FilmVu filmVu =
        unFilmVu()
            .avecFilmId(50)
            .avecUtilisateurId(UTILISATEUR_ID)
            .avecNote("10/10")
            .avecCommentaire("Vive le Cinéma !")
            .build();

    // When
    FilmVu filmVuAjoute = databaseFilmVuRepository.ajouterUnFilmVu(filmVu);

    // Then
    assertThat(filmVuAjoute).isEqualTo(filmVu);
  }
}

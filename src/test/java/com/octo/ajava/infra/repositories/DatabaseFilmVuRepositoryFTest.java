package com.octo.ajava.infra.repositories;

import static com.octo.ajava.fixtures.FilmVuTestFixture.unFilmVu;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.utility.MountableFile.forClasspathResource;

import com.octo.ajava.domain.FilmVu;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
class DatabaseFilmVuRepositoryFTest { // TODO

  private static final int FILM_ID = 5;
  private static final String UTILISATEUR_ID = "jdurant";

  private FilmVu filmVuExistant;

  @Autowired private DatabaseFilmVuDAO databaseFilmVuDAO;
  @Autowired private DatabaseFilmVuRepository databaseFilmVuRepository;

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

  @DisplayName("#chercherUnFilmVu")
  @Nested
  class ChercherUnFilmVuTest {

    @DisplayName("devrait chercher et renvoyer un FilmVu par filmId et utilisateurId déjà existant")
    @Test
    void chercherFilmVuExistant() throws Exception {
      // When
      FilmVu filmVuTrouve = databaseFilmVuRepository.chercherUnFilmVu(FILM_ID, UTILISATEUR_ID);

      // Then
      assertThat(filmVuTrouve).isEqualTo(filmVuExistant);
    }

    @DisplayName("devrait renvoyer null lorsqu'un FilmVu par filmId et utilisateurId n'existe")
    @Test
    void chercherUnFilmVuNonExistant() throws Exception {
      // Given
      int filmIdErrone = 10;

      // When
      FilmVu filmVuTrouve = databaseFilmVuRepository.chercherUnFilmVu(filmIdErrone, UTILISATEUR_ID);

      // Then
      assertThat(filmVuTrouve).isNull();
    }
  }

  @DisplayName("devrait ajouter un FilmVu en BDD et le renvoyer")
  @Test
  void ajouterFilmVu() throws Exception {
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

  @DisplayName("devrait modifier un FilmVu déjà existant")
  @Test
  void modifierFilmVu() throws Exception {
    // Given
    FilmVu filmVuExistant =
        databaseFilmVuRepository.ajouterUnFilmVu(
            unFilmVu()
                .avecFilmId(1)
                .avecUtilisateurId(UTILISATEUR_ID)
                .avecNote("08/10")
                .avecCommentaire("Le Parrain est cool !")
                .build());

    filmVuExistant.setNote("10/10");
    filmVuExistant.setCommentaire("Le Parrain finalement c'est génial !");

    // When
    FilmVu filmVuModifie = databaseFilmVuRepository.modifierUnFilmVu(filmVuExistant);

    // Then
    assertThat(filmVuModifie.getId()).isEqualTo(filmVuExistant.getId());
    assertThat(filmVuModifie.getFilmId()).isEqualTo(1);
    assertThat(filmVuModifie.getUtilisateurId()).isEqualTo(UTILISATEUR_ID);
    assertThat(filmVuModifie.getNote()).isEqualTo("10/10");
    assertThat(filmVuModifie.getCommentaire()).isEqualTo("Le Parrain finalement c'est génial !");
  }

  @DisplayName("devrait renvoyer la liste de tous les FilmVu par un utilisateur")
  @Test
  void renvoyerFilmVusUtilisateur() throws Exception {
    // Given
    // TODO

    // When
    List<FilmVu> filmVusTrouves =
        databaseFilmVuRepository.chercherDesFilmsVusParUnUtilisateur(UTILISATEUR_ID);

    // Then
    assertThat(filmVusTrouves).hasSize(3);
  }
}

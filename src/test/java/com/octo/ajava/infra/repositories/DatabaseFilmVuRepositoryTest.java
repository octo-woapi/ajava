package com.octo.ajava.infra.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.octo.ajava.domain.FilmVu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.MountableFile;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class DatabaseFilmVuRepositoryTest {

  private final String userId = "jdurant";

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

  @Test
  public void doit_ajouter_un_utilisateur_et_retourner_le_film_ajoute_en_base() {
    // given
    var filmVu = new FilmVu(1, userId, "10/10", "Batman c'est ouf");
    // when
    var result = databaseFilmVuRepository.ajouterUnFilmVu(filmVu);
    // then
    assertThat(result).isEqualTo(filmVu);
  }
}

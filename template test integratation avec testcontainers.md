template de test d'intégration avec testcontainers
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class DatabaseFilmVuRepositoryTest {
    @Inject
    private DatabaseRepository databaseRepository;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer =
        new PostgreSQLContainer<>("postgres:14-alpine")
            .withCopyFileToContainer(
                MountableFile.forClasspathResource("/docker_postgres_init.sql"),
                "/docker-entrypoint-initdb.d/"
            );

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void mon_test() {
        // given

        // when
        var result = databaseRepository.fetch();

        // then
        assertThat(result).isEqualTo();
    }
}
```
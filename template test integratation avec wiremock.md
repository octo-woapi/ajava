template de test d'intégration avec wiremock
```java
@WireMockTest
class MonRepositoryTest {

    private static MonRepository monRepository;

    @BeforeAll()
    public static void prepare(WireMockRuntimeInfo wmRuntimeInfo) {
        var monMapper = new MonMapper();
        var httpClient = new HttpClient("mon-token-secret", wmRuntimeInfo.getHttpBaseUrl());
        monRepository = new MonRepository(httpClient, monMapper);
    }

    @Test
    public void mon_test() {
        // given
        stubFor(get("/api/client").willReturn(ok()));
        
        // when
        monRepository.handle();
        
        // then
        verify(getRequestedFor(urlEqualTo("/api/client")));
    }
}
```
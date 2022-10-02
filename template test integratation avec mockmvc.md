template de test d'intégration avec mock mvc 
```java
@WebMvcTest(MonController.class)
class MonControllerTest extends ApiIntegrationTest {

    @MockBean
    private MonUseCase monUseCaseMocked;

    @Test
    public void mon_test() {
        // given
        when(monUseCaseMocked.maFonction()).thenReturn();

        // when / then
        mockMvc
            .perform(
                get("/api/test").contentType(APPLICATION_JSON).with(httpBasic("user", "password")))
            .andExpect(status().isOk())
            .andExpect(
                content()
                    .json(
                        """
                            {
                                "id": 1,
                                "label": "fake label"
                            }
                            """)
            );
    }
}
```
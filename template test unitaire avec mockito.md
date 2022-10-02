template de test unitaire
```java
@ExtendWith(MockitoExtension.class)
public class MonUseCaseTest {

    @Mock private MonRepository monRepository;

    @InjectMocks private MonUseCase monUseCase;

    @Test
    public void mon_test() {
        // Given
        when(monRepository.handle()).thenReturn();

        // When
        var result =

        // Then
        assertThat(result).isEqualTo();
    }
}
```
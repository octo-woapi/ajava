package com.octo.ajava.infra.controllers.oauth2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.octo.ajava.ApiIntegrationTest;
import com.octo.ajava.infra.controllers.UtilisateurApi;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(OAuth2Controller.class)
public class OAuth2ControllerTest extends ApiIntegrationTest {

  private static final String URL_OAUTH2 = "/oauth2/";
  private static final String URL_OAUTH2_USER_ME = URL_OAUTH2 + "user/me";

  @Test
  void devrait_renvoyer_http_401_quand_la_route_est_appelee_sans_authentification()
      throws Exception {
    laRouteEstProtegee(get(URL_OAUTH2));
  }

  @Test
  void devrait_renvoyer_http_200_avec_un_message_de_presentation() throws Exception {
    // Given
    String messageAttendu = "Accès avec OAUTH 2.0 - Bienvenue à la formation AJAVA !";

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(get(URL_OAUTH2).headers(avecAuthentification()).contentType(APPLICATION_JSON))
            .andReturn()
            .getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(response.getContentAsString()).isEqualTo(messageAttendu);
  }

  @Test
  void devrait_renvoyer_les_informations_de_l_utilisateur_authentifie_avec_le_role_USER()
      throws Exception {
    // Given
    final String userId = "jdurant";
    final List<String> roles = List.of("USER");

    UtilisateurApi utilisateurApiAttendu = new UtilisateurApi(userId, roles);

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(
                get(URL_OAUTH2_USER_ME)
                    .headers(avecAuthentification(userId, roles))
                    .contentType(APPLICATION_JSON))
            .andReturn()
            .getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(convertirJsonEnObjet(response, UtilisateurApi.class))
        .isEqualTo(utilisateurApiAttendu);
  }
}

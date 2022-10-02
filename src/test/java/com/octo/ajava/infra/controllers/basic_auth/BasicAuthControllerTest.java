package com.octo.ajava.infra.controllers.basic_auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.octo.ajava.ApiIntegrationTest;
import com.octo.ajava.infra.controllers.UtilisateurApi;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;

@WebMvcTest(BasicAuthController.class)
class BasicAuthControllerTest extends ApiIntegrationTest {

  private static final String URL_BASIC = "/basic/";
  private static final String URL_BASIC_USER_ME = URL_BASIC + "user/me";

  @Test
  void devrait_renvoyer_http_200_avec_un_message_de_presentation() throws Exception {
    // Given
    String messageAttendu = "Accès avec BASIC AUTH - Bienvenue à la formation AJAVA !";

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(get(URL_BASIC).with(httpBasic("user", "password")))
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
    UtilisateurApi utilisateurApiAttendu = new UtilisateurApi("jdurant", List.of("ROLE_USER"));

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(get(URL_BASIC_USER_ME).with(httpBasic("jdurant", "password")))
            .andReturn()
            .getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(convertirJsonEnObjet(response, UtilisateurApi.class))
        .isEqualTo(utilisateurApiAttendu);
  }

  @Test
  void devrait_renvoyer_les_informations_de_l_utilisateur_authentifie_avec_les_roles_ADMIN_et_USER()
      throws Exception {
    // Given
    UtilisateurApi utilisateurApiAttendu =
        new UtilisateurApi("admin", List.of("ROLE_ADMIN", "ROLE_USER"));

    // When
    MockHttpServletResponse response =
        mockMvc
            .perform(get(URL_BASIC_USER_ME).with(httpBasic("admin", "password")))
            .andReturn()
            .getResponse();

    // Then
    assertThat(response.getStatus()).isEqualTo(OK.value());
    assertThat(convertirJsonEnObjet(response, UtilisateurApi.class))
        .isEqualTo(utilisateurApiAttendu);
  }
}

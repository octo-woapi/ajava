package com.octo.ajava.infra.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "security_auth",
    type = SecuritySchemeType.OAUTH2,
    flows =
        @OAuthFlows(
            authorizationCode =
                @OAuthFlow(
                    authorizationUrl =
                        "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/auth",
                    tokenUrl =
                        "${spring.security.oauth2.resourceserver.jwt.issuer-uri}/protocol/openid-connect/token",
                    scopes = {@OAuthScope(name = "roles")})))
@OpenAPIDefinition(
    info = @Info(title = "API Films", version = "v1", description = "Documentation de l'API Films"),
    servers =
        @Server(
            url = "${server.servlet.context-path:/}",
            description = "URL par défaut (relative)"),
    security = @SecurityRequirement(name = "security_auth"))
public class SwaggerConfig {

  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
        .group("tout")
        .pathsToMatch("/**")
        .packagesToScan("com.octo.ajava")
        .build();
  }
}

package com.octo.ajava.infra.configuration;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;
import static org.springdoc.core.models.GroupedOpenApi.builder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(type = HTTP, name = "basicAuth", scheme = "basic")
@OpenAPIDefinition(
    info = @Info(title = "API Films", version = "v1", description = "Documentation de l'API Films"),
    servers =
        @Server(
            url = "${server.servlet.context-path:/}",
            description = "URL par défaut (relative)"),
    security = @SecurityRequirement(name = "security_auth"))
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi api() {
    return builder().group("tout").pathsToMatch("/**").packagesToScan("com.octo.ajava").build();
  }
}

package com.octo.ajava.infra.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "API Films", version = "v1", description = "Documentation de l'API Films"),
    servers =
        @Server(
            url = "${server.servlet.context-path:/}",
            description = "URL par défaut (relative)"))
public class SwaggerConfig {

  public GroupedOpenApi api() {
    return GroupedOpenApi.builder()
        .group("tout")
        .pathsToMatch("/**")
        .packagesToScan("com.octo.ajava")
        .build();
  }
}

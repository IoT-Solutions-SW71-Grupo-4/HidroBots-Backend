package org.hidrobots.platform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI learningPlatformOpenApi() {
        // General Configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("HIGN API")
                        .description("HIGN application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("HIGN 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("HIGN Wiki Documentation")
                        .url("https://acme-learning-platform.wiki.github.io/docs"));

        // Add security scheme

        final String securitySchemeName = "bearerAuth";

        return openApi;
    }
}

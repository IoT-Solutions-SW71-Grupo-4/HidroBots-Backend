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
                        .title("HidroBots API")
                        .description("HidroBots application REST API documentation.")
                        .version("v1.0.0")
                        .license(new License().name("HIGN 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("HidroBots Documentation")
                        .url("https://github.com/IoT-Solutions-SW71-Grupo-4/HidroBots-Report"));

        // Add security scheme

        final String securitySchemeName = "bearerAuth";

        return openApi;
    }
}

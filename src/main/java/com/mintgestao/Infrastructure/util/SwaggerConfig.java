package com.mintgestao.Infrastructure.util;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Token",
                                new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                        .name("Token")
                                        .in(SecurityScheme.In.HEADER)
                        )).addSecurityItem(new SecurityRequirement().addList("Token"));
    }
}

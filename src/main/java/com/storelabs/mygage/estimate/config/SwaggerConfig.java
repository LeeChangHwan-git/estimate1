package com.storelabs.mygage.estimate.config;

import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("견적 시스템 API Document")
                .version("v1.0")
                .description("견적 어플리케이션 API 명세서");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
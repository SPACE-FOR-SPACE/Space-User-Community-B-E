package com.example.spacecommunityuserbe.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title("Space Community User Be").description("난 잘 모르겠다"));
  }
}

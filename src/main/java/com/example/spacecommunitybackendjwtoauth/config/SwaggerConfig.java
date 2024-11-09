package com.example.spacecommunitybackendjwtoauth.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpaceCommunity")
                        .description("""
                                ### 오류 메시지 예시:
                                ```json
                                {
                                  "status": 401,
                                  "errorCode": "UNAUTHENTICATED_ACCESS",
                                  "message": "인증이 필요합니다.",
                                  "timestamp": "2024-09-29T10:00:00"
                                }
                                ```""")
                        .version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("jwtAuth"))
                .components(new Components()
                        .addSecuritySchemes("jwtAuth", new SecurityScheme()
                                .name("JWT Authentication")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("Bearer")
                                .bearerFormat("JWT")
                                .description("""
                                        Access Token은 Authorization 헤더의 Bearer: accessToken 형식으로 저장
                                        Refresh Token은 HttpOnly Cookie에 저장
                                        """)

                        ))
                .addServersItem(new Server().url("/"));
    }
}

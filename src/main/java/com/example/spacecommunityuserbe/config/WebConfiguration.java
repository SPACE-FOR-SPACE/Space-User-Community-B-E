package com.example.spacecommunityuserbe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOriginPatterns("*")  // allowedOrigins 대신 사용
            .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP method
            .allowCredentials(true); // 쿠키 인증 요청 허용

  }
}
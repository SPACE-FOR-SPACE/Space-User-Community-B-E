package com.example.spacecommunitybackendjwtoauth.config;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.auth.service.impl.CustomUserDetailsServiceImpl;
import com.example.spacecommunitybackendjwtoauth.exception.filter.SpaceSecurityExceptionFilter;
import com.example.spacecommunitybackendjwtoauth.jwt.filter.*;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


// Security Configuration
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final List<String> excludedPaths = Arrays.asList("/swagger/**", "/api-docs/**", "/user/reissue");

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        ProviderManager providerManager = (ProviderManager) configuration.getAuthenticationManager();
        providerManager.getProviders().add(authenticationProvider());
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(customUserDetailsService, passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors((cors) -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOriginPatterns(Collections.singletonList("*"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization", "SET-COOKIE"));
                    config.setMaxAge(3600L);
                    return config;
                }))
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET,
                                "/user/broadcast",
                                "/user/profile/{username}",
                                "/user/reissue",
                                "/search/user/{search}",
                                "/search/doc/{search}",
                                "/community/doclist",
                                "/community/doclist/{username}",
                                "/community/doc/{docId}",
                                "/community/comment/{docId}",
                                "/community/recomment/{commentId}").permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/user/sendEmail",
                                "/user/verifyEmail",
                                "/user/login",
                                "/user/register",
                                "/user/verifyEmail").permitAll()
                        .requestMatchers(
                                "/community/doc",
                                "/community/comment",
                                "/community/recomment").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET,
                                "/user/logout").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH,
                                "/user/update").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/user/delete",
                                "/community/doc/{docId}",
                                "/community/comment/{commentId}",
                                "/community/recomment/{recommentId}").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST,
                                "/community/doc/like",
                                "/community/doc/unlike",
                                "/user/report").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET,
                                "/admin/ban/{username}",
                                "/admin/unban/{username}",
                                "/admin/banlist",
                                "/admin/report/{reportId}",
                                "/admin/reportlist").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/broadcast/{broadcastId}",
                                "/admin/report/{reportId}",
                                "/admin/doc/{docId}",
                                "/admin/comment/{commentId}",
                                "/admin/recomment/{recommentId}").hasAnyRole( "ADMIN")
                        .requestMatchers("/broadcast").hasAnyRole( "ADMIN")
                        .anyRequest().denyAll()
                )
                .addFilterAfter(new SpaceSecurityExceptionFilter(objectMapper), CorsFilter.class)
                .addFilterAfter(new CustomJWTFilter(jwtUtil, excludedPaths), CorsFilter.class)
                .addFilterBefore(new CustomLoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, objectMapper, "/user/login"), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(new CustomLogoutFilter(jwtUtil, refreshTokenRepository), LogoutFilter.class)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
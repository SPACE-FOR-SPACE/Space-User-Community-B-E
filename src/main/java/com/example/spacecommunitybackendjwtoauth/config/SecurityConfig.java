package com.example.spacecommunitybackendjwtoauth.config;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.auth.service.CustomUserDetailsService;
import com.example.spacecommunitybackendjwtoauth.exception.filter.SpaceSecurityExceptionFilter;
import com.example.spacecommunitybackendjwtoauth.jwt.filter.CustomAuthenticationProvider;
import com.example.spacecommunitybackendjwtoauth.jwt.filter.CustomJWTFilter;
import com.example.spacecommunitybackendjwtoauth.jwt.filter.CustomLoginFilter;
import com.example.spacecommunitybackendjwtoauth.jwt.filter.CustomLogoutFilter;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration // 컴포넌트로 Bean 등록
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final List<String> excludedPaths = Arrays.asList("/swagger-ui/**", "/v3//api-docs/**", "/reissue");

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
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
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.GET, "/community/words/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/community/comments/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/community/wordlists/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/profile/**").permitAll()
                        .requestMatchers("/community/words/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/community/comments/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/logout").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/update").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/user/delete").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/report").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole( "ADMIN")
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
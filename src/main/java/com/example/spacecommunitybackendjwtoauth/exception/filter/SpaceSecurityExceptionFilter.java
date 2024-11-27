package com.example.spacecommunitybackendjwtoauth.exception.filter;

import com.example.spacecommunitybackendjwtoauth.exception.ErrorResponse;
import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityAuthException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// Space 관련 인증 오류 필터
@RequiredArgsConstructor
public class SpaceSecurityExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, AuthenticationException {
        try {
            filterChain.doFilter(request, response);
        }
        catch(SpaceCommunityAuthException se) {
            handleSpaceSecurityException(response, se);
        }
    }

    private void handleSpaceSecurityException(HttpServletResponse response, SpaceCommunityAuthException e) throws IOException {
        response.setStatus(e.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        ErrorResponse errorResponse = ErrorResponse.from(e.getHttpStatus().value(), e.getErrorCode(), e.getMessage());

        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}

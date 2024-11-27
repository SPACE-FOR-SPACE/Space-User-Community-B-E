package com.example.spacecommunitybackendjwtoauth.jwt.filter;

import com.example.spacecommunitybackendjwtoauth.jwt.exception.DuplicateLoginException;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.InvalidTokenException;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.domain.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

// JWTFilter 권한 부여 Filter
@RequiredArgsConstructor
public class CustomJWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final List<String> excludedPaths;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest req, @NonNull HttpServletResponse res, @NonNull FilterChain chain) throws ServletException, IOException {

        String requestURI = req.getRequestURI();

        if(requestURI.equals("/user/login")) {
            String accessToken = jwtUtil.getAccessTokenFromHeaders(req);
            String refreshToken = jwtUtil.getRefreshTokenFromCookies(req);
            if(jwtUtil.jwtVerify(accessToken, "access") || jwtUtil.jwtVerify(refreshToken, "refresh")) {
                throw new DuplicateLoginException();
            }
            chain.doFilter(req, res);
            return;
        }

        String accessToken = jwtUtil.getAccessTokenFromHeaders(req);

        if(accessToken == null || accessToken.equals("Bearer null")) {
            chain.doFilter(req, res);
            return;
        }

        accessToken = accessToken.replaceFirst("Bearer ", "");

        jwtUtil.isExpired(accessToken);

        String category = jwtUtil.getCategory(accessToken);

        if(!category.equals("access")) {
            throw new InvalidTokenException();
        }

        Long userId = jwtUtil.getUserId(accessToken);
        Role role = jwtUtil.getRole(accessToken);

        GrantedAuthority authority = new SimpleGrantedAuthority(role.getValue());

        Authentication authToken = new UsernamePasswordAuthenticationToken(userId, null, Collections.singletonList(authority));

        SecurityContextHolder.getContext().setAuthentication(authToken);

        chain.doFilter(req, res);
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        return excludedPaths.stream()
                .anyMatch(pattern -> new AntPathMatcher().match(pattern, request.getServletPath()));
    }
}

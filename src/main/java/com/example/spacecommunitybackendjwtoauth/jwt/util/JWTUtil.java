package com.example.spacecommunitybackendjwtoauth.jwt.util;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.JWTUserDTO;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.user.Role;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final RefreshTokenRepository refreshTokenRepository;

    JWTUtil(@Value("${spring.jwt.secret}") String secretKey, RefreshTokenRepository refreshTokenRepository) {
        this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Value("${spring.jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${spring.jwt.refresh.expiration}")
    private long refreshTokenExpiration;

    public Long getUserId(String token) {
        return Long.valueOf(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", String.class));
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public Role getRole(String token) {
        String roleValue = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
        return Role.fromValue(roleValue);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createAccessToken(Long id, Role role) {
        return createJWT("access", id, role, accessTokenExpiration);
    }

    public String createRefreshToken(Long id, Role role) {
        return createJWT("refresh", id, role, refreshTokenExpiration);
    }

    public ResponseCookie createRefreshTokenCookie(String key, String email, String refreshToken, Role role) {
        JWTUserDTO jwtUserDTO = JWTUserDTO.builder()
                .email(email)
                .refreshToken(refreshToken)
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .role(role.toString())
                .build();
        refreshTokenRepository.save(jwtUserDTO);
        return createCookie(key, refreshToken, (int) refreshTokenExpiration);
    }

    public String getAccessTokenFromHeaders(HttpServletRequest request) {
        if(request.getHeader("Authorization") != null) {
            return request.getHeader("Authorization");
        }
        return null;
    }

    public String getRefreshTokenFromCookies(HttpServletRequest request) {
        if(request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> "refreshToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    private String createJWT(String category, Long userId, Role role, Long expiredMs) {
        return Jwts.builder()
                .claim("userId", String.valueOf(userId))
                .claim("role", role.getValue())
                .claim("category", category)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    private ResponseCookie createCookie(String key, String value, int maxAge) {
        return ResponseCookie.from(key, value)
                .maxAge(maxAge)
                .path("/")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .build();
    }

}

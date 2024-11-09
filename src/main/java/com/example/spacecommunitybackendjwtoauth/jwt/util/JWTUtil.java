package com.example.spacecommunitybackendjwtoauth.jwt.util;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.JWTUserDTO;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.ExpiredRefreshTokenException;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.ExpiredTokenException;
import com.example.spacecommunitybackendjwtoauth.user.Role;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

// JWT Utility
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

    public String getEmail(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public Long getUserId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", Long.class);
    }

    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public boolean jwtVerify(String token, String type) {
        try {
            if(token == null) return false;
            if(token.replaceFirst("Bearer ", "").equals("null")) return false;
            String category = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
            return Objects.equals(category, type);
        }
        catch(JwtException e) {
            return false;
        }
    }

    public Role getRole(String token) {
        String roleValue = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
        return Role.fromValue(roleValue);
    }

    public void isExpired(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date(System.currentTimeMillis()));
        }
        catch(ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }

    public String createAccessToken(Long id, String email, Role role) {
        return createJWT("access", id, email, role, accessTokenExpiration);
    }

    public String createRefreshToken(Long id, String email, Role role) {
        return createJWT("refresh", id, email, role, refreshTokenExpiration);
    }

    public ResponseCookie invalidRefreshCookie(String category) {
        return createCookie(category, "", 0);
    }

    public ResponseCookie createRefreshTokenCookie(String refreshToken, Long userId, String email, Role role) {
        JWTUserDTO jwtUserDTO = JWTUserDTO.builder()
                .userId(userId)
                .email(email)
                .refreshToken(refreshToken)
                .role(role.toString())
                .build();
        refreshTokenRepository.save(jwtUserDTO);
        return createCookie("refreshToken", refreshToken, (int) refreshTokenExpiration);
    }

    public String getAccessTokenFromHeaders(HttpServletRequest request) {
        if(request.getHeader(HttpHeaders.AUTHORIZATION) != null) {
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

    private String createJWT(String category, Long userId, String email, Role role, Long expiredMs) {
        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
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

    public void tokenVerify(String token, String type) {
        if(token == null) throw new ExpiredRefreshTokenException();
        String tokenType = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
        if(!Objects.equals(tokenType, type)) throw new ExpiredRefreshTokenException();
        isExpired(token);
    }

}

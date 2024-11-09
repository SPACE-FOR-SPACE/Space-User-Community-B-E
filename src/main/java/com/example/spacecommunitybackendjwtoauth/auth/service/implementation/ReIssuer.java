package com.example.spacecommunitybackendjwtoauth.auth.service.implementation;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.RefreshTokenNotFoundException;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.ServerTokenException;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// 토큰 재발급 서비스
@Service
@RequiredArgsConstructor
public class ReIssuer {

    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = jwtUtil.getRefreshTokenFromCookies(request);

        if(refreshToken == null) {
            throw new RefreshTokenNotFoundException();
        }

        jwtUtil.tokenVerify(refreshToken, "refresh");

        if(!refreshTokenRepository.existsById(refreshToken)) {
            throw new ServerTokenException();
        }

        Long userId = jwtUtil.getUserId(refreshToken);
        String email = jwtUtil.getEmail(refreshToken);
        Role role = jwtUtil.getRole(refreshToken);

        String newAccessToken = jwtUtil.createAccessToken(userId, email, role);
        String newRefreshToken = jwtUtil.createRefreshToken(userId, email, role);

        refreshTokenRepository.deleteById(refreshToken);

        response.addHeader("Authorization", "Bearer " + newAccessToken);
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshTokenCookie(newRefreshToken, userId, email, role).toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

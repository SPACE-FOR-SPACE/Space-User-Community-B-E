package com.example.spacecommunitybackendjwtoauth.auth.service.impl;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.auth.service.ReIssuer;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.ExpiredRefreshTokenException;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.RefreshTokenNotFoundException;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.ServerTokenException;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.domain.Role;
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
public class ReIssuerImpl implements ReIssuer {

    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Override
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        String refreshToken = jwtUtil.getRefreshTokenFromCookies(request);

        if(refreshToken == null) {
            throw new RefreshTokenNotFoundException();
        }
        try {
            jwtUtil.tokenVerify(refreshToken, "refresh");
        }
        catch(ExpiredRefreshTokenException e) {
            response.addHeader("Authorization", "Bearer ");
            response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.invalidRefreshCookie("refreshToken").toString());
            throw new ExpiredRefreshTokenException();
        }

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

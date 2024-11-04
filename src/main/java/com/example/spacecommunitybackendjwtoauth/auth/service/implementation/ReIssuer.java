package com.example.spacecommunitybackendjwtoauth.auth.service.implementation;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.JWTUserDTO;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.ExpiredRefreshTokenException;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.InvalidTokenException;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.RefreshTokenNotFoundException;
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

        if(!jwtUtil.isExpired(refreshToken)) {
            throw new ExpiredRefreshTokenException();
        }

        if(!jwtUtil.getCategory(refreshToken).equals("refresh")) {
            throw new InvalidTokenException();
        }

        if(!refreshTokenRepository.existsById(refreshToken)) {
            throw new RefreshTokenNotFoundException();
        }

        String email = jwtUtil.getEmail(refreshToken);
        Role role = jwtUtil.getRole(refreshToken);

        String newAccessToken = jwtUtil.createAccessToken(email, role);
        String newRefreshToken = jwtUtil.createRefreshToken(email, role);

        refreshTokenRepository.deleteById(refreshToken);

        JWTUserDTO newJWTUserDTO = JWTUserDTO.builder()
                .refreshToken(newRefreshToken)
                .role(role.toString())
                .email(email)
                .build();

        refreshTokenRepository.save(newJWTUserDTO);

        response.addHeader("Authorization", "Bearer " + newAccessToken);
        response.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshTokenCookie(email, refreshToken, role).toString());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

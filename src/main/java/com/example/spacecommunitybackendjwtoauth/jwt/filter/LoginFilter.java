package com.example.spacecommunitybackendjwtoauth.jwt.filter;

import com.example.spacecommunitybackendjwtoauth.jwt.record.LoginRequestRecord;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.Role;
import com.example.spacecommunitybackendjwtoauth.auth.service.dto.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, ObjectMapper objectMapper, String loginURL) {
        super.setFilterProcessesUrl(loginURL);
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            LoginRequestRecord loginRequest = objectMapper.readValue(req.getInputStream(), LoginRequestRecord.class);

            String email = loginRequest.email();
            String password = loginRequest.password();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(email, password);

            return authenticationManager.authenticate(authRequest);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();

        Long id = customUserDetails.getId();
        Role role = customUserDetails.getRole();
        String email = customUserDetails.getEmail();

        String accessToken = jwtUtil.createAccessToken(id, role);
        String refreshToken = jwtUtil.createRefreshToken(id, role);

        res.addHeader("Authorization", "Bearer " + accessToken);
        res.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshTokenCookie("refreshToken", email, refreshToken, role).toString());
        res.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) {
        res.setStatus(401);
    }

}

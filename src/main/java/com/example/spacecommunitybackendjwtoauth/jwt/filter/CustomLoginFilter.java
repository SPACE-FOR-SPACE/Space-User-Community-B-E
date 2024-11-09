package com.example.spacecommunitybackendjwtoauth.jwt.filter;

import com.example.spacecommunitybackendjwtoauth.jwt.record.LoginUserDTO;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import com.example.spacecommunitybackendjwtoauth.user.Role;
import com.example.spacecommunitybackendjwtoauth.auth.presentation.dto.CustomUserDetails;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.InvalidMethodException;
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

// CustomLoginFiler
public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public CustomLoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, ObjectMapper objectMapper, String loginURL) {
        super.setFilterProcessesUrl(loginURL);
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            if(!req.getMethod().equals("POST")) throw new InvalidMethodException();
            LoginUserDTO loginRequest = objectMapper.readValue(req.getInputStream(), LoginUserDTO.class);

            String email = loginRequest.email();
            String password = loginRequest.password();

            System.out.println("email, password" + email + password);

            if(email == null || password == null) throw new IllegalArgumentException();

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

        String email = customUserDetails.getEmail();
        Role role = customUserDetails.getRole();
        Long userId = customUserDetails.getUserId();

        String accessToken = jwtUtil.createAccessToken(userId, email, role);
        String refreshToken = jwtUtil.createRefreshToken(userId, email, role);

        res.addHeader("Authorization", "Bearer " + accessToken);
        res.addHeader(HttpHeaders.SET_COOKIE, jwtUtil.createRefreshTokenCookie(refreshToken, userId, email, role).toString());
        res.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) {
        res.setStatus(401);
    }

}

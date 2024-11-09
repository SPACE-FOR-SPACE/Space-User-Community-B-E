package com.example.spacecommunitybackendjwtoauth.jwt.filter;

import com.example.spacecommunitybackendjwtoauth.auth.presentation.repository.RefreshTokenRepository;
import com.example.spacecommunitybackendjwtoauth.jwt.exception.AnonymousException;
import com.example.spacecommunitybackendjwtoauth.jwt.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

// CustomLogoutFilter
@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, filterChain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if(!requestURI.equals("/user/logout") || !request.getMethod().equals("GET")){
            filterChain.doFilter(request, response);
            return;
        }

        String refreshTokenJWT = jwtUtil.getRefreshTokenFromCookies(request);

        if(refreshTokenJWT == null) {
            throw new AnonymousException();
        }

        LogoutProcess(refreshTokenJWT, response);
    }

    private void LogoutProcess(String refreshToken, HttpServletResponse response) {
        jwtUtil.isExpired(refreshToken);

        if(!jwtUtil.getCategory(refreshToken).equals("refresh")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if(!refreshTokenRepository.existsById(refreshToken)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        refreshTokenRepository.deleteById(refreshToken);

        response.addHeader("Authorization", "Bearer ");

        ResponseCookie invalidRefreshCookie = jwtUtil.invalidRefreshCookie("refresh");
        response.addHeader(HttpHeaders.SET_COOKIE, invalidRefreshCookie.toString());

        response.setStatus(HttpServletResponse.SC_OK);
    }
}

package com.example.spacecommunitybackendjwtoauth.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface ReIssuer {
    ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response);
}

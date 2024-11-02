package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.filter.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotFoundException extends SpaceCommunityAuthException {
    public RefreshTokenNotFoundException() {
        super(HttpStatus.UNAUTHORIZED, "REFRESH_TOKEN_NOT_FOUND", "리프레시 토큰이 존재하지 않습니다.");
    }
}

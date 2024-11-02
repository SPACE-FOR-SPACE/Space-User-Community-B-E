package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.filter.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;

public class ExpiredRefreshTokenException extends SpaceCommunityAuthException {
    public ExpiredRefreshTokenException() {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_REFRESH_TOKEN", "재로그인 해야 합니다.");
    }
}

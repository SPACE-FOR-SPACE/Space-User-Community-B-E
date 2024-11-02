package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.filter.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends SpaceCommunityAuthException {
    public ExpiredTokenException() {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_TOKEN", "만료된 토큰입니다.");
    }
}

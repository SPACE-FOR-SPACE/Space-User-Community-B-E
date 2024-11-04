package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.filter.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;

public class ExpiredAccessTokenException extends SpaceCommunityAuthException {
    public ExpiredAccessTokenException() {
        super(HttpStatus.UNAUTHORIZED, "EXPIRED_ACCESS_TOKEN", "만료된 토큰입니다.");
    }
}

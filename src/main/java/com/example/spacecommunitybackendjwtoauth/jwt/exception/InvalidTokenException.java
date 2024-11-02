package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.filter.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends SpaceCommunityAuthException {
    public InvalidTokenException() {
        super(HttpStatus.UNAUTHORIZED, "INVALID_TOKEN", "유효하지 않은 토큰입니다.");
    }
}

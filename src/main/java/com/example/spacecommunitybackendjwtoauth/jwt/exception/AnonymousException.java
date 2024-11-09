package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;

public class AnonymousException extends SpaceCommunityAuthException {
    public AnonymousException() {
        super(HttpStatus.UNAUTHORIZED, "DUPLICATE_LOGOUT", "이미 로그아웃한 상태입니다.");
    }
}

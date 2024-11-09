package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;

public class DuplicateLoginException extends SpaceCommunityAuthException {
    public DuplicateLoginException() {
        super(HttpStatus.UNAUTHORIZED, "DUPLICATE_LOGIN", "이미 로그인한 상태입니다.");
    }
}

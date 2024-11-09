package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRouteException;
import org.springframework.http.HttpStatus;

public class InvalidMethodException extends SpaceCommunityRouteException {
    public InvalidMethodException() {
        super(HttpStatus.FORBIDDEN, "메소드 요청이 올바르지 않습니다.");
    }
}

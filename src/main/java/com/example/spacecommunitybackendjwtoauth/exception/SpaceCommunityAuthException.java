package com.example.spacecommunitybackendjwtoauth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

// Space Auth Exception
@Getter
public class SpaceCommunityAuthException extends AuthenticationException {
    private final HttpStatus httpStatus;
    private final String errorCode;

    public SpaceCommunityAuthException(HttpStatus httpStatus, String errorCode, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}

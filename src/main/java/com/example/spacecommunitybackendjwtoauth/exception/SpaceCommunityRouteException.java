package com.example.spacecommunitybackendjwtoauth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// Space Auth Exception
@Getter
public class SpaceCommunityRouteException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public SpaceCommunityRouteException(HttpStatus httpStatus, String errorMessage) {
        super(errorMessage);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}

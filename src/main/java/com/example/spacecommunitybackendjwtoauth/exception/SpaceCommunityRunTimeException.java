package com.example.spacecommunitybackendjwtoauth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// Space Runtime Exception
@Getter
public class SpaceCommunityRunTimeException extends RuntimeException {
    private final HttpStatus status;
    private final String errorMessage;

    public SpaceCommunityRunTimeException(HttpStatus status, String errorMessage) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
    }
}

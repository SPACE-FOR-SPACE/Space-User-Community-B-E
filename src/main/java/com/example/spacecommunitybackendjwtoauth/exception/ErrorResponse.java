package com.example.spacecommunitybackendjwtoauth.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String errorCode,
        String message,
        LocalDateTime timestamp
) {
    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.getStatus().value(),
                errorCode.name(),
                errorCode.getMessage(),
                LocalDateTime.now()
        );
    }

    public static ErrorResponse from(int httpStatus, String errorCode, String message) {
        return new ErrorResponse(
                httpStatus,
                errorCode,
                message,
                LocalDateTime.now()
        );
    }
}
package com.example.spacecommunityuserbe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_EXISTS(400, "User Already Exists"),
    USER_NOT_AUTHENTICATED(400, "User Not Authenticated"),
    DATABASE_ERROR(500, "Database Error"),
    SESSION_NOT_FOUND(400, "Session Not Found"),
    SESSION_EXSITS(400, "Session Already Exists");

    private final int code;
    private final String message;
}

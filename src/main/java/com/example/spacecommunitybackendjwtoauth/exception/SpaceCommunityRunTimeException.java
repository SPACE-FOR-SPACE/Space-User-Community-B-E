package com.example.spacecommunitybackendjwtoauth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SpaceCommunityRunTimeException extends RuntimeException {
    private final ErrorCode errorCode;
}

package com.example.spacecommunitybackendjwtoauth.auth.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class EmailCodeNotCorrectException extends SpaceCommunityRunTimeException {
    public EmailCodeNotCorrectException() {
        super(HttpStatus.BAD_REQUEST, "이메일 발송코드와 일치하지 않습니다.");
    }
}

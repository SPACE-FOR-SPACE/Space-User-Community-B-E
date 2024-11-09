package com.example.spacecommunitybackendjwtoauth.user.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class UserLoginException extends SpaceCommunityRunTimeException {
    public UserLoginException() {
        super(HttpStatus.BAD_REQUEST, "이메일 혹은 비밀번호가 올바르지 않습니다.");
    }
}

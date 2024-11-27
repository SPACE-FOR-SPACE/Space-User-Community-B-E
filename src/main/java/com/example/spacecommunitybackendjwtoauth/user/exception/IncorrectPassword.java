package com.example.spacecommunitybackendjwtoauth.user.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class IncorrectPassword extends SpaceCommunityRunTimeException {
    public IncorrectPassword() {
        super(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다.");
    }
}

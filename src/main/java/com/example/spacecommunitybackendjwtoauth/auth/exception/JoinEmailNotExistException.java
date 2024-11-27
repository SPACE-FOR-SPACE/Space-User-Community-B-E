package com.example.spacecommunitybackendjwtoauth.auth.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class JoinEmailNotExistException extends SpaceCommunityRunTimeException {
    public JoinEmailNotExistException() {
        super(HttpStatus.BAD_REQUEST, "회원가입 절차를 받는 이메일이 아닙니다.");
    }
}
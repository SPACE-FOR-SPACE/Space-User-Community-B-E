package com.example.spacecommunitybackendjwtoauth.jwt.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;

public class ServerTokenException extends SpaceCommunityAuthException {
    public ServerTokenException() {
        super(HttpStatus.UNAUTHORIZED, "SERVER_TOKEN_EXCEPTION", "서버에서 토큰 인증이 오류났습니다.");
    }
}

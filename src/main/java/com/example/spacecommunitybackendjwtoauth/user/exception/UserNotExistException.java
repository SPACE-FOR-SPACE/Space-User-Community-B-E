package com.example.spacecommunitybackendjwtoauth.user.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class UserNotExistException extends SpaceCommunityRunTimeException {
    public UserNotExistException() {
        super(HttpStatus.BAD_REQUEST, "유저를 찾을 수 없습니다.");
    }
}

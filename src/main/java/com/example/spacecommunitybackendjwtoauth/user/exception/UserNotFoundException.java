package com.example.spacecommunitybackendjwtoauth.user.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends SpaceCommunityRunTimeException {
    public UserNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "유저를 찾을 수 없습니다.");
    }
}

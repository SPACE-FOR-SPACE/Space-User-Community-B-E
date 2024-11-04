package com.example.spacecommunitybackendjwtoauth.user.exceptions;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends SpaceCommunityRunTimeException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.");
    }
}

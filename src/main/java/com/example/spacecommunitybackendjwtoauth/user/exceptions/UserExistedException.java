package com.example.spacecommunitybackendjwtoauth.user.exceptions;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class UserExistedException extends SpaceCommunityRunTimeException {
    public UserExistedException() {
        super(HttpStatus.CONFLICT, "유저가 이미 존재합니다.");
    }
}
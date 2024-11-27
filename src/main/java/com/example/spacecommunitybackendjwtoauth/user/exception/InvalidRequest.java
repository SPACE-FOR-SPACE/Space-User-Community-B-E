package com.example.spacecommunitybackendjwtoauth.user.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class InvalidRequest extends SpaceCommunityRunTimeException {
    public InvalidRequest() {
        super(HttpStatus.BAD_REQUEST, "아무 값이 들어오지 않았습니다.");
    }
}

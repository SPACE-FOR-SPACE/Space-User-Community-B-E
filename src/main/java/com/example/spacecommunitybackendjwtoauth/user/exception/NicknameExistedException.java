package com.example.spacecommunitybackendjwtoauth.user.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class NicknameExistedException extends SpaceCommunityRunTimeException {
    public NicknameExistedException() {
        super(HttpStatus.CONFLICT, "해당 닉네임이 이미 존재합니다.");
    }
}

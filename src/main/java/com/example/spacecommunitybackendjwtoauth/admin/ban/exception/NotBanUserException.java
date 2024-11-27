package com.example.spacecommunitybackendjwtoauth.admin.ban.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class NotBanUserException extends SpaceCommunityRunTimeException {
    public NotBanUserException() {
        super(HttpStatus.BAD_REQUEST, "밴이 되지 않은 유저입니다.");
    }
}

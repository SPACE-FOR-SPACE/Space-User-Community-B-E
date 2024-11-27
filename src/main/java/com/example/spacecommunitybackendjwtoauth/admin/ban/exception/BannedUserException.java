package com.example.spacecommunitybackendjwtoauth.admin.ban.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class BannedUserException extends SpaceCommunityRunTimeException {
    public BannedUserException() {
        super(HttpStatus.BAD_REQUEST, "밴이 된 유저입니다.");
    }
}

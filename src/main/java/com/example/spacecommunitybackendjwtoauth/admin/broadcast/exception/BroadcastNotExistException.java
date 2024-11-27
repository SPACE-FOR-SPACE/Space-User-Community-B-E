package com.example.spacecommunitybackendjwtoauth.admin.broadcast.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class BroadcastNotExistException extends SpaceCommunityRunTimeException {
    public BroadcastNotExistException() {
        super(HttpStatus.BAD_REQUEST, "해당 ID의 공지는 존재하지 않습니다.");
    }
}

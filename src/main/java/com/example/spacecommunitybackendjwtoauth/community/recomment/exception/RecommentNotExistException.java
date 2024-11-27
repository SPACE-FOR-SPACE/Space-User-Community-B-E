package com.example.spacecommunitybackendjwtoauth.community.recomment.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class RecommentNotExistException extends SpaceCommunityRunTimeException {
    public RecommentNotExistException() {
        super(HttpStatus.BAD_REQUEST, "해당 ID의 대댓글은 존재하지 않습니다.");
    }
}

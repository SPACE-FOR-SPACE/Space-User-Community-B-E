package com.example.spacecommunitybackendjwtoauth.community.comment.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class CommentNotExistException extends SpaceCommunityRunTimeException {
    public CommentNotExistException() {
        super(HttpStatus.BAD_REQUEST, "해당 ID의 댓글은 존재하지 않습니다.");
    }
}

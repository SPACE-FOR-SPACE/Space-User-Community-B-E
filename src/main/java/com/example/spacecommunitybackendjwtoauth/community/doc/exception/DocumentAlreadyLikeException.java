package com.example.spacecommunitybackendjwtoauth.community.doc.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class DocumentAlreadyLikeException extends SpaceCommunityRunTimeException {
    public DocumentAlreadyLikeException() {
        super(HttpStatus.BAD_REQUEST, "해당 ID의 문서를 이미 좋아요 했습니다.");
    }
}

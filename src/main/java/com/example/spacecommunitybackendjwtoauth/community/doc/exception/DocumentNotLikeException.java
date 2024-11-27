package com.example.spacecommunitybackendjwtoauth.community.doc.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class DocumentNotLikeException extends SpaceCommunityRunTimeException {
    public DocumentNotLikeException() {
        super(HttpStatus.BAD_REQUEST, "해당 ID의 문서를 좋아요하지 않았습니다.");
    }
}

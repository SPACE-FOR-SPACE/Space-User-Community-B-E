package com.example.spacecommunitybackendjwtoauth.community.doc.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class DocumentNotExistException extends SpaceCommunityRunTimeException {
    public DocumentNotExistException() {
        super(HttpStatus.BAD_REQUEST, "해당 ID의 문서를 찾지 못했습니다.");
    }
}

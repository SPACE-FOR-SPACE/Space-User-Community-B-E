package com.example.spacecommunitybackendjwtoauth.community.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class NotPermissionException extends SpaceCommunityRunTimeException {
    public NotPermissionException() {
        super(HttpStatus.BAD_REQUEST, "해당 문서를 지울 권한이 없습니다.");
    }
}

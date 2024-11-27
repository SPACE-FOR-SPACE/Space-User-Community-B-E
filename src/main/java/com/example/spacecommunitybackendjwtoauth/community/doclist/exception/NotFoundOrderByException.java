package com.example.spacecommunitybackendjwtoauth.community.doclist.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class NotFoundOrderByException extends SpaceCommunityRunTimeException {
    public NotFoundOrderByException() {
        super(HttpStatus.BAD_REQUEST, "해당 정렬방식은 존재하지 않습니다.");
    }
}

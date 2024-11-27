package com.example.spacecommunitybackendjwtoauth.admin.report.exception;

import com.example.spacecommunitybackendjwtoauth.exception.SpaceCommunityRunTimeException;
import org.springframework.http.HttpStatus;

public class ReportNotExistException extends SpaceCommunityRunTimeException {
    public ReportNotExistException() {
        super(HttpStatus.BAD_REQUEST, "해당 신고 페이지는 존재하지 않습니다.");
    }
}

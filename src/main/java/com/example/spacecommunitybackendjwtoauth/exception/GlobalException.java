package com.example.spacecommunitybackendjwtoauth.exception;

import com.example.spacecommunitybackendjwtoauth.exception.filter.SpaceCommunityAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SpaceCommunityRunTimeException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(SpaceCommunityRunTimeException e) {
        ErrorResponse response = ErrorResponse.from(e.getStatus().value(), e.getStatus().toString(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(response);
    }

    @ExceptionHandler(SpaceCommunityAuthException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(SpaceCommunityAuthException e) {
        int httpStatus = e.getHttpStatus().value();
        String errorCode = e.getErrorCode();
        String message = e.getMessage();
        ErrorResponse errorResponse = ErrorResponse.from(httpStatus, errorCode, message);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleDefineException() {
        return ResponseEntity.status(400).body(ErrorResponse.from(400, "INVALID_INPUT", "데이터 타입이 일치하지 않습니다."));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException() {
        return ResponseEntity.status(400).body(ErrorResponse.from(400, "UNVERIFIED_INPUT", "데이터 값이 유효하지 않습니다"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException() {
        return ResponseEntity.status(400).body(ErrorResponse.from(400, "INVALID_INPUT", "잘못된 값이 들어왔습니다."));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException() {
        return ResponseEntity.status(400).body(ErrorResponse.from(400, "INVALID_INPUT" , "잘못된 NULL값이 들어갔습니다."));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException() {
        return ResponseEntity.status(500).body(ErrorResponse.from(500, "SERVER_UNKNOWN","서버에서 알 수 없는 에러가 발생했습니다."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException() {
        return ResponseEntity.status(500).body(ErrorResponse.from(500, "SERVER_UNKNOWN", "서버에서 알 수 없는 에러가 발생했습니다."));
    }
}

package com.example.spacecommunityuserbe.exception;

import com.example.spacecommunityuserbe.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorDTO> handleServerException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO(500, "Interval Server Error");
        return new ResponseEntity<>(errorDTO, HttpStatus.valueOf(errorDTO.getErrorCode()));
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorDTO> handleCustomException(CustomException ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.errorCode.getCode(), ex.errorCode.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.valueOf(errorDTO.getErrorCode()));
    }

}

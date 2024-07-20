package com.enigma.controller;

import com.enigma.dto.response.CommonResponse;
import com.enigma.utils.exception.ResourceNotFoundException;
import com.enigma.utils.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<CommonResponse<String>> handleResourceNotFoundExeption(ResourceNotFoundException ex) {
        CommonResponse<String> response = new CommonResponse<String>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setData(Optional.empty());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<CommonResponse<String>> handleValidationExeption(ValidationException ex) {
        CommonResponse<String> response=new CommonResponse<String>();
        response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
        response.setMessage(ex.getMessage());
        response.setData(Optional.empty());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }
}

package com.tesa.hospitalerd.exception;

import com.tesa.hospitalerd.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<?> handleValidationException(jakarta.validation.ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseBuilder.error("Validation failed: " + ex.getMessage()));
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        StringBuilder details = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            details.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseBuilder.error("Validation failed: " + details));
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadable(org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseBuilder.error("Malformed JSON request: " + ex.getMessage()));
    }

    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseBuilder.error("Entity not found: " + ex.getMessage()));
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(org.springframework.dao.DataIntegrityViolationException ex) {
        String details = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ResponseBuilder.error("Data integrity violation: " + details));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseBuilder.error("Internal server error: " + ex.getMessage()));
    }

}

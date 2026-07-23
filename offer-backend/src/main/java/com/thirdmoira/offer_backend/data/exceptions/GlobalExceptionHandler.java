package com.thirdmoira.offer_backend.data.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EmailAlreadyExistException.class,NameAlreadyExistException.class})
    public ResponseEntity<Map<String, Object>> handleAlreadyExistException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.BAD_REQUEST.value(),
                        "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler({UserNotExistException.class})
    public ResponseEntity<Map<String, Object>> handleUserNotExistException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler({MeNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleMeNotFoundException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.CONFLICT.value(),
                        "error", HttpStatus.CONFLICT.getReasonPhrase(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Map<String, Object>> handleUnauthorizedException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.UNAUTHORIZED.value(),
                        "error", HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler({ForbiddenActionException.class})
    public ResponseEntity<Map<String, Object>> handleForbiddenException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.FORBIDDEN.value(),
                        "error", HttpStatus.FORBIDDEN.getReasonPhrase(),
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler({ServiceNotFoundException.class, ServiceCategoryNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleServiceNotFoundException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                        "message", ex.getMessage()
                ));
    }
}

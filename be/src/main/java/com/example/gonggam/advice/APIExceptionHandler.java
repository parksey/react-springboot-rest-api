package com.example.gonggam.advice;

import com.example.gonggam.customer.exception.CustomerException;
import com.example.gonggam.owner.exception.OwnerException;
import com.example.gonggam.reservation.exception.ReservationException;
import com.example.gonggam.space.exception.SharedSpaceException;
import com.example.gonggam.util.exception.CustomException;
import com.example.gonggam.util.exception.CustomValidationStatus;
import org.hibernate.SessionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler({CustomerException.class,
            OwnerException.class,
            ReservationException.class,
            SharedSpaceException.class,
            SessionException.class})
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception) {
        return handleExceptionInternal(exception.getValidationStatus());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleGlobalException(RuntimeException runtimeException) {
        return handleExceptionInternal(HttpStatus.NOT_FOUND, runtimeException.getMessage());
    }

    private ResponseEntity<ErrorResponse> handleExceptionInternal(CustomValidationStatus exceptionCode) {
        return ResponseEntity.status(exceptionCode.getHttpStatus())
                .body(errorResponse(exceptionCode.name(), exceptionCode.getMessage()));
    }

    private ResponseEntity<ErrorResponse> handleExceptionInternal(HttpStatus httpStatus, String message) {
        return ResponseEntity.status(httpStatus)
                .body(errorResponse(httpStatus.getReasonPhrase(), message));
    }

    private ErrorResponse errorResponse(String code, String message) {
        return new ErrorResponse(code, message);
    }
}

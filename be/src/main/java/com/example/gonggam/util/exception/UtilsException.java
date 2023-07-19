package com.example.gonggam.util.exception;

public class UtilsException extends RuntimeException{

    public UtilsException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
    }
}

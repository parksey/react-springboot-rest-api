package com.example.gonggam.space.exception;

import com.example.gonggam.util.exception.CustomValidationStatus;

public class SharedSpaceException extends RuntimeException {

    public SharedSpaceException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
    }
}

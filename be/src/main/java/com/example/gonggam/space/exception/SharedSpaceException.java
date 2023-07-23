package com.example.gonggam.space.exception;

import com.example.gonggam.util.exception.CustomException;
import com.example.gonggam.util.exception.CustomValidationStatus;

public class SharedSpaceException extends RuntimeException implements CustomException  {

    private final CustomValidationStatus validationStatus;

    public SharedSpaceException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
        this.validationStatus = validationStatus;
    }

    public CustomValidationStatus getValidationStatus() {
        return validationStatus;
    }
}

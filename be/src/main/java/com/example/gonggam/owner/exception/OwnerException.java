package com.example.gonggam.owner.exception;

import com.example.gonggam.util.exception.CustomException;
import com.example.gonggam.util.exception.CustomValidationStatus;

public class OwnerException extends RuntimeException implements CustomException {

    private final CustomValidationStatus validationStatus;

    public OwnerException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
        this.validationStatus = validationStatus;
    }

    public CustomValidationStatus getValidationStatus() {
        return validationStatus;
    }
}

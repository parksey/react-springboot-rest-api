package com.example.gonggam.customer.exception;

import com.example.gonggam.util.exception.CustomException;
import com.example.gonggam.util.exception.CustomValidationStatus;

public class CustomerException extends RuntimeException implements CustomException {

    private final CustomValidationStatus validationStatus;

    public CustomerException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
        this.validationStatus = validationStatus;
    }

    public CustomValidationStatus getValidationStatus() {
        return validationStatus;
    }
}

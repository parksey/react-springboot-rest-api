package com.example.gonggam.owner.exception;

import com.example.gonggam.util.exception.CustomValidationStatus;

public class OwnerException extends RuntimeException{

    public OwnerException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
    }
}

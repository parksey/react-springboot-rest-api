package com.example.gonggam.customer.exception;

import com.example.gonggam.util.exception.CustomValidationStatus;

public class CustomerException extends RuntimeException{

    public CustomerException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
    }
}

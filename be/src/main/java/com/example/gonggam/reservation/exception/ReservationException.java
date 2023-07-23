package com.example.gonggam.reservation.exception;

import com.example.gonggam.util.exception.CustomException;
import com.example.gonggam.util.exception.CustomValidationStatus;

public class ReservationException extends RuntimeException implements CustomException {

    private final CustomValidationStatus validationStatus;

    public ReservationException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
        this.validationStatus = validationStatus;
    }

    public CustomValidationStatus getValidationStatus() {
        return validationStatus;
    }
}

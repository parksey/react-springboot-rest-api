package com.example.gonggam.reservation.exception;

import com.example.gonggam.util.exception.CustomValidationStatus;

public class ReservationException extends RuntimeException{

    public ReservationException(CustomValidationStatus validationStatus) {
        super(validationStatus.getMessage());
    }
}

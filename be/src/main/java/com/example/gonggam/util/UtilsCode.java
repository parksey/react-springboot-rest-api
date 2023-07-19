package com.example.gonggam.util;

import com.example.gonggam.util.exception.CustomValidationStatus;
import com.example.gonggam.util.exception.UtilsException;
import com.example.gonggam.util.exception.ValidationStatus;

public class UtilsCode {

    public static final class Global {

        public static final String PHONE_PATTERN = "\\d{10,11}";
        public static final String EMAIL_PATTERN = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}";


        private Global() {
            throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
        }
    }

    public static final class Space {

        public static final int MIN_SPACE_CAPACITY = 1;
        public static final String DATE_FORMAT = "yyyy-MM-dd HH:MM";

        private Space() {
            throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
        }
    }

    private UtilsCode() {
        throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
    }
}

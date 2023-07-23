package com.example.gonggam.util;

import com.example.gonggam.util.exception.CustomValidationStatus;
import com.example.gonggam.util.exception.UtilsException;

public class UtilsCode {

    public static final class Global {

        public static final String PHONE_PATTERN = "\\d{10,11}";
        public static final String EMAIL_PATTERN = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}";
        public static final String SECRET_KEY = "시크릿키";
        public static final String OWNER_NO = "ownerNo";
        public static final String EMAIL = "email";

        private Global() {
            throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
        }
    }

    public static final class Space {

        public static final int MIN_SPACE_CAPACITY = 1;
        public static final int MIN_MONEY = 0;
        public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

        private Space() {
            throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
        }
    }

    private UtilsCode() {
        throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
    }
}

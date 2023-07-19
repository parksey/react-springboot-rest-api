package com.example.gonggam.util.exception;

public final class ValidationStatus {

    public static class Global {

        public static final String NOT_EMAIL = "이메일 형식에 맞지 않습니다.";
        public static final String NOT_PHONE_PATTERN = "핸드폰 패턴이 맞지 않습니다.";
        public static final String NO_DATA = "값이 안들어 왔습니다.";
        public static final String NOT_BLANK = "빈 값이면 안됩니다.";

        private Global() {
            throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
        }
    }

    public static class  Space {

        public static final String UNDER_MIN_CAPACIRY = "최소 인원은 {0}명입니다.";

        private Space() {
            throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
        }
    }

    public static class  Owner {

        public static final String NO_OWNER_NUMBER = "사업자 번호가 없습니다.";

        private Owner() {
            throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
        }
    }

    private ValidationStatus() {
        throw new UtilsException(CustomValidationStatus.UTIL_CLASS);
    }
}

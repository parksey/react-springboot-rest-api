package com.example.gonggam.util.exception;

public enum CustomValidationStatus {

    UTIL_CLASS("유틸 클래스 입니다."),
    EXIST_OWNER("이미 동일한 사업자가 등록되어 있습니다."),
    TIME_ERROR("시작일자와 종료일자가 잘못되었습니다.");

    private final String message;

    CustomValidationStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

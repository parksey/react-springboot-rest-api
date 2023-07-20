package com.example.gonggam.util.exception;

public enum CustomValidationStatus {

    UTIL_CLASS("유틸 클래스 입니다."),
    NOT_CONSTRUCT_CLASS("생성자를 생성할 수 없는 클래스입니다."),
    EXIST_OWNER("이미 동일한 사업자가 등록되어 있습니다."),
    NO_OWNER("해당 사업자가 없습니다."),
    TIME_ERROR("시작일자와 종료일자가 잘못되었습니다."),
    NO_SHAED_SPACE("해당 사업자는 어떠한 공유 공간도 입력하시지 않았습니다.");

    private final String message;

    CustomValidationStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

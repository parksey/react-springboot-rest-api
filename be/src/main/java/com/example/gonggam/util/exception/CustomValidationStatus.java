package com.example.gonggam.util.exception;

import org.springframework.http.HttpStatus;

public enum CustomValidationStatus {

    UTIL_CLASS("유틸 클래스 입니다.", HttpStatus.LOCKED),
    NOT_CONSTRUCT_CLASS("생성자를 생성할 수 없는 클래스입니다.", HttpStatus.LOCKED),
    EXIST_OWNER("이미 동일한 사업자가 등록되어 있습니다.", HttpStatus.CONFLICT),
    NO_OWNER("해당 사업자가 없습니다.", HttpStatus.NOT_FOUND),
    TIME_ERROR("시작일자와 종료일자가 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    NO_SHARED_SPACE("아직 남아있는 공유 공간이 있습니다.", HttpStatus.CONFLICT),
    EXIST_USER("해당 이메일은 이미 사용 중입니다.", HttpStatus.CONFLICT),
    CHECK_AGAIN("아이디나 비밀번호를 확인해 주세요", HttpStatus.BAD_REQUEST),
    NO_SPACE("해당 공간이 없습니다.", HttpStatus.NOT_FOUND),
    LOGIN_ERROR("로그인 에러 입니다.", HttpStatus.BAD_REQUEST),
    NO_USER("회원가입되어 있지 않는 유저 입니다.", HttpStatus.NOT_FOUND),
    ALREADY_RESERVED("이미 예약했습니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus httpStatus;

    CustomValidationStatus(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

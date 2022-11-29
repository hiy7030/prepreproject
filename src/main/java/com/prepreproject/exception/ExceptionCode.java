package com.prepreproject.exception;

import lombok.Getter;

public enum ExceptionCode { // 사용자 정의 예외

    MEMBER_NOT_FOUND(404, "Member Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    MEMBER_EXISTS(409,"Member Exists"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

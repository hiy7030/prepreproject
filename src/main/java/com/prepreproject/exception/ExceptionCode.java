package com.prepreproject.exception;

import lombok.Getter;

public enum ExceptionCode { // 사용자 정의 예외

    MEMBER_NOT_FOUND(404, "Member Not Found"),
    COFFEE_NOT_FOUND(404, "Coffee Not Found"),
    ORDER_NOT_FOUND(404, "Order Not Found"),
    CANNOT_CANCEL_ORDER(403, "CanNot Cancel Order"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    MEMBER_EXISTS(409,"Member Exists"),
    COFFEE_EXISTS(409, "Coffee Exists"),
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

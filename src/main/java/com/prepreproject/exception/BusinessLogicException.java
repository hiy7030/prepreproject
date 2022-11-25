package com.prepreproject.exception;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException{ // RuntimeException을 상속받아 더 구체적인 예외 정보를 제공하도록 함
    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage()); // 상위클래스인 RuntimeException에게 해당 예외 메세지를 전달
        this.exceptionCode = exceptionCode;
    }


}

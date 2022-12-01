package com.prepreproject.advice.exception;

import com.prepreproject.exception.BusinessLogicException;
import com.prepreproject.exception.ExceptionCode;
import com.prepreproject.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        final ErrorResponse response = ErrorResponse.of(e.getBindingResult());

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        final ErrorResponse response = ErrorResponse.of(e.getConstraintViolations());
        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse BusinessLoginExceptionHandler(BusinessLogicException e) {
        final ErrorResponse response = ErrorResponse.of(e.getExceptionCode());

        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        final ErrorResponse response = ErrorResponse.of(ExceptionCode.METHOD_NOT_ALLOWED);
        return response;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(NullPointerException e) {
        final ErrorResponse response = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
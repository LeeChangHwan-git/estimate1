package com.storelabs.mygage.estimate.exception;

import com.storelabs.mygage.estimate.enums.ErrorCode;

public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode, String... args) {
        super(errorCode.getMessage(args));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}

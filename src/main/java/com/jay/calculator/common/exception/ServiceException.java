package com.jay.calculator.common.exception;

public class ServiceException extends Exception {

    private ErrorCodeEnum errorCodeEnum;

    public ServiceException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(msg);
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

}

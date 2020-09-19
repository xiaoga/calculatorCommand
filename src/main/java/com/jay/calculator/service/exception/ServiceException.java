package com.jay.calculator.service.exception;

public class ServiceException extends Exception {

    private ErrorCodeEnum errorCodeEnum;

    public ServiceException() {
        super();
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(ErrorCodeEnum errorCodeEnum, String msg, Throwable cause) {
        super(msg, cause);
        this.errorCodeEnum = errorCodeEnum;
    }
    public ServiceException(ErrorCodeEnum errorCodeEnum,String msg) {
        super(msg);
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public void setErrorCodeEnum(ErrorCodeEnum errorCodeEnum) {
        this.errorCodeEnum = errorCodeEnum;
    }
}

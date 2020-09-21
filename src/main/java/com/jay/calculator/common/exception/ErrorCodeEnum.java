package com.jay.calculator.common.exception;

public enum ErrorCodeEnum {

    ERROR_COMMANDLINE_EMPTY("ERROR_COMMANDLINE_EMPTY"),
    ERROR_INSUFFICIENT_PARAM("ERROR_INSUFFICIENT_PARAM"),
    ERROR_PARAM_IS_NOT_NUMBER("ERROR_PARAM_IS_NOT_NUMBER"),
    ERROR_UNDO_INFO_MISMATCH("ERROR_UNDO_INFO_MISMATCH"),
    ERROR_DENOMINATOR_IS_ZERO("ERROR_DENOMINATOR_IS_ZERO");

    private String code;

    ErrorCodeEnum(String code) {
        this.code = code;
    }
}

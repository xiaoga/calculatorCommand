package com.jay.calculator.command;

import com.jay.calculator.command.cmd.*;

public enum OperatorCommandEnum {
    /**
     * you can register command here once you have new operator and implement the logic with command
     */
    ADD("+", CommandAdd.class),
    MINUS("-", CommandMinus.class),
    TIMES("*", CommandTimes.class),
    DIVIDE("/", CommandDivide.class),
    CLEAR("clear", CommandClear.class),
    SQRT("sqrt", CommandSqrt.class),
    UNDO("undo", CommandUndo.class);

    private String operator;
    private Class cls;

    OperatorCommandEnum(String operator, Class cls) {
        this.operator = operator;
        this.cls = cls;
    }

    public String getOperator() {
        return operator;
    }

    public Class getCls() {
        return cls;
    }

    public static OperatorCommandEnum getByOperator(String operator) {
        OperatorCommandEnum rst = null;
        OperatorCommandEnum[] enums = OperatorCommandEnum.values();
        for (OperatorCommandEnum en : enums) {
            boolean match = en.getOperator().equals(operator);
            if (match) {
                rst = en;
                break;
            }
        }
        return rst;
    }

}

package com.jay.calculator;

import com.jay.calculator.facade.CalculatorFacadeImpl;
import com.jay.calculator.service.command.CommandExecutorImpl;
import com.jay.calculator.service.command.cmd.*;

public enum RegisterBeanEnum {
    /**
     * you can register Beam here once you have new bean implement
     */
    CalculatorFacadeImpl(CalculatorFacadeImpl.class),
    CommandExecutorImpl(CommandExecutorImpl.class),
    CommandAdd(CommandAdd.class),
    CommandMinus(CommandMinus.class),
    CommandDivide(CommandDivide.class),
    CommandTimes(CommandTimes.class),
    CommandClear(CommandClear.class),
    CommandSqrt(CommandSqrt.class),
    UNDO(CommandUndo.class);

    private Class cls;

    RegisterBeanEnum(Class cls) {
        this.cls = cls;
    }

    public Class getCls() {
        return cls;
    }
}

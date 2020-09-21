package com.jay.calculator.container;

import com.jay.calculator.calculate.CalculatorFacadeImpl;
import com.jay.calculator.calculate.commandline.CommandLineCalculate;
import com.jay.calculator.calculate.commandline.CommandLineExit;
import com.jay.calculator.calculate.commandline.CommandLineMan;
import com.jay.calculator.command.CommandExecutorImpl;
import com.jay.calculator.command.CommandQueryServiceImpl;
import com.jay.calculator.command.cmd.*;
import com.jay.calculator.command.dal.DataDaoImpl;

public enum RegisterBeanEnum {
    /**
     * you can register Beam here once you have new bean implement
     */
    CalculatorFacadeImpl(CalculatorFacadeImpl.class),
    CommandExecutorImplEnum(CommandExecutorImpl.class),
    DataDaoImpl(DataDaoImpl.class),
    CalculateCommandBase(CalculateCommandBase.class),
    CommandQueryServiceImplEnum(CommandQueryServiceImpl.class),
    // register command line
    CommandLineCalculate(CommandLineCalculate.class),
    CommandLineMan(CommandLineMan.class),
    CommandLineExit(CommandLineExit.class),
    // register command
    CommandAdd(CommandAdd.class),
    CommandMinus(CommandMinus.class),
    CommandDivide(CommandDivide.class),
    CommandTimes(CommandTimes.class),
    CommandClear(CommandClear.class),
    CommandSqrt(CommandSqrt.class),
    CommandUndo(CommandUndo.class);

    private Class cls;

    RegisterBeanEnum(Class cls) {
        this.cls = cls;
    }

    public Class getCls() {
        return cls;
    }
}

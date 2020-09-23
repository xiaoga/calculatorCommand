package com.jay.calculator.calculate;

import com.jay.calculator.calculate.commandline.CommandLineCalculate;
import com.jay.calculator.calculate.commandline.CommandLineExit;
import com.jay.calculator.calculate.commandline.CommandLineMan;

public enum CommandLineEnum {
    /**
     * you can register command here once you have new operator and implement the logic with command
     */
    CALCULATE("CALCULATE", CommandLineCalculate.class),
    EXIT("exit", CommandLineExit.class),
    MAN("man", CommandLineMan.class);

    private String commandLine;
    private Class cls;

    CommandLineEnum(String commandLine, Class cls) {
        this.commandLine = commandLine;
        this.cls = cls;
    }

    public String getOperator() {
        return commandLine;
    }

    public Class getCls() {
        return cls;
    }

    public static CommandLineEnum getByCommandLine(String commandLine) {
        CommandLineEnum rst = null;
        CommandLineEnum[] enums = CommandLineEnum.values();
        for (CommandLineEnum en : enums) {
            boolean match = en.getOperator().equals(commandLine);
            if (match) {
                rst = en;
                break;
            }
        }
        return rst;
    }

}

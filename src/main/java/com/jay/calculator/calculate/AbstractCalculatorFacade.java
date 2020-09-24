package com.jay.calculator.calculate;

import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;

public abstract class AbstractCalculatorFacade implements CalculatorFacade {

    public void processCommand(String command) throws ServiceException {
        preProcess(command);
        process(command);
        postProcess(command);
    }

    protected void preProcess(String command) throws ServiceException {
        boolean commandIsNull = command == null || command.trim().length() == 0;
        if (commandIsNull) {
            throw new ServiceException(ErrorCodeEnum.ERROR_COMMANDLINE_EMPTY, "command line is empty");
        }
    }

    protected void postProcess(String command) {
        // do something after process
    }

    protected abstract void process(String command) throws ServiceException;
}

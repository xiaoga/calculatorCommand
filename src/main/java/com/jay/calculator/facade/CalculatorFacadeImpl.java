package com.jay.calculator.facade;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.CommandExecutor;
import com.jay.calculator.service.command.CommandExecutorImpl;
import com.jay.calculator.service.exception.ServiceException;

public class CalculatorFacadeImpl extends AbstractCalculatorFacade implements CalculatorFacade {


    @Override
    protected void process(String command) throws ServiceException {
        String[] commandArr = command.split(" ");
        for (String cmd : commandArr) {
            CommandExecutor commandExecutor = (CommandExecutorImpl) ApplicationContext.getContext().get(CommandExecutorImpl.class);
            commandExecutor.execute(cmd);
        }

    }

}

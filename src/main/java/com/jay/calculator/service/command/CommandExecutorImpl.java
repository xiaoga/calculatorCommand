package com.jay.calculator.service.command;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.cmd.CalculateCommand;
import com.jay.calculator.service.command.cmd.CalculateCommandBase;
import com.jay.calculator.service.exception.ServiceException;

public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void execute(String command) throws ServiceException {
        OperatorCommandEnum en = OperatorCommandEnum.getByOperator(command);
        boolean existOperator = en != null;
        if (existOperator) {
            Class cls = en.getCls();
            getCommandByInput(cls).processCommand();
        } else {
            CalculateCommandBase inputCommand = (CalculateCommandBase) ApplicationContext.getContext().get(CalculateCommandBase.class);
            inputCommand.inputData(command);
        }
    }

    private CalculateCommand getCommandByInput(Class cls) {
        CalculateCommand cmd = (CalculateCommand) ApplicationContext.getContext().get(cls);
        return cmd;
    }
}

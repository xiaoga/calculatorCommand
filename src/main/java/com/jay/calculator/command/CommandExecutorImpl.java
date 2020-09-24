package com.jay.calculator.command;


import com.jay.calculator.command.cmd.CalculateCommand;
import com.jay.calculator.command.cmd.CalculateCommandBase;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.AutoWired;
import com.jay.calculator.container.bean.Service;

@Service
public class CommandExecutorImpl implements CommandExecutor {

    @AutoWired(type = CalculateCommandBase.class)
    private CalculateCommandBase commandBase;

    @Override
    public void execute(String command) throws ServiceException {
        OperatorCommandEnum en = OperatorCommandEnum.getByOperator(command);
        boolean existOperator = en != null;
        if (existOperator) {
            Class cls = en.getCls();
            getCommandByInput(cls).processCommand();
        } else {
            commandBase.inputData(command);
        }
    }

    private CalculateCommand getCommandByInput(Class cls) {
        return (CalculateCommand) ApplicationContext.getContext().get(cls);
    }
}

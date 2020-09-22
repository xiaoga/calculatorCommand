package com.jay.calculator.command;


import com.jay.calculator.command.cmd.CalculateCommand;
import com.jay.calculator.command.cmd.CalculateCommandBase;
import com.jay.calculator.command.model.CommandExecutorRequest;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.Service;

@Service
public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void execute(String command) throws ServiceException {
        OperatorCommandEnum en = OperatorCommandEnum.getByOperator(command);
        CalculateCommandBase commandBase = (CalculateCommandBase) ApplicationContext.getContext().get(CalculateCommandBase.class);
        boolean existOperator = en != null;
        if (existOperator) {
            Class cls = en.getCls();
                getCommandByInput(cls).processCommand();
        } else {
            commandBase.inputData(command);
        }
    }

    private CalculateCommand getCommandByInput(Class cls) {
        CalculateCommand cmd = (CalculateCommand) ApplicationContext.getContext().get(cls);
        return cmd;
    }
}

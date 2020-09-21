package com.jay.calculator.command;


import com.jay.calculator.command.cmd.CalculateCommand;
import com.jay.calculator.command.cmd.CalculateCommandBase;
import com.jay.calculator.command.model.CommandExecutorRequest;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;

public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void execute(CommandExecutorRequest request) throws ServiceException {
        String command = request.getCommand();
        OperatorCommandEnum en = OperatorCommandEnum.getByOperator(command);
        CalculateCommandBase commandBase = (CalculateCommandBase) ApplicationContext.getContext().get(CalculateCommandBase.class);

        boolean existOperator = en != null;
        if (existOperator) {
            Class cls = en.getCls();
            try {
                getCommandByInput(cls).processCommand();
            } catch (ServiceException e) {
                boolean insufficientParam = e.getErrorCodeEnum().equals(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM);
                if (insufficientParam) {
                    Integer position = request.getPosition();
                    //[requirement] to meet the requirement of error printing
                    throw new ServiceException(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, "operator <" + command + "> (position:" + position + "): insufficient parameters");
                }
            }
        } else {
            commandBase.inputData(command);
        }
    }

    private CalculateCommand getCommandByInput(Class cls) {
        CalculateCommand cmd = (CalculateCommand) ApplicationContext.getContext().get(cls);
        return cmd;
    }
}

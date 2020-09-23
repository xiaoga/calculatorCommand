package com.jay.calculator.calculate.commandline;

import com.jay.calculator.command.CommandExecutor;
import com.jay.calculator.command.CommandExecutorImpl;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class CommandLineCalculate implements CommandLine {
    @Override
    public void doCommand(String commandLine) throws ServiceException {
        List<String> operationList = new ArrayList<String>();
        String[] commandArr = commandLine.split(" ");
        for (int i = 0; i < commandArr.length; i++) {
            String cmd = commandArr[i];
            operationList.add(cmd);
            CommandExecutor commandExecutor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
            try {
                commandExecutor.execute(cmd);
            } catch (ServiceException e) {
                boolean insufficientParam = ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM.equals(e.getErrorCodeEnum());
                if (insufficientParam) {
                    //[requirement] to meet the requirement of error printing
                    throw new ServiceException(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, "operator <" + cmd + "> (position:" + getIndexOfOperationFromString(commandLine, operationList) + "): insufficient parameters");
                } else {
                    throw e;
                }
            }
        }
    }

    private int getIndexOfOperationFromString(String command, List<String> operationList) {
        int index = 0;
        for (String operation : operationList) {
            index = command.indexOf(operation, index + 1);
        }
        return index + 1;
    }
}

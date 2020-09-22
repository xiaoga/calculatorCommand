package com.jay.calculator.calculate.commandline;

import com.jay.calculator.command.CommandExecutor;
import com.jay.calculator.command.CommandExecutorImpl;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.Service;

@Service()
public class CommandLineCalculate implements CommandLine {
    @Override
    public void doCommand(String commandLine) throws ServiceException {
        String[] commandArr = commandLine.split(" ");
        for (int i = 0; i < commandArr.length; i++) {
            String cmd = commandArr[i];
            CommandExecutor commandExecutor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
            try {
                commandExecutor.execute(cmd);
            } catch (ServiceException e) {
                boolean insufficientParam = e.getErrorCodeEnum().equals(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM);
                if (insufficientParam) {
                    //[requirement] to meet the requirement of error printing
                    throw new ServiceException(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, "operator <" + cmd + "> (position:" + i + "): insufficient parameters");
                }
            }
        }


    }
}

package com.jay.calculator.calculate.commandline;

import com.jay.calculator.command.CommandExecutor;
import com.jay.calculator.command.CommandExecutorImpl;
import com.jay.calculator.command.model.CommandExecutorRequest;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;

public class CommandLineCalculate implements CommandLine {
    @Override
    public void doCommand(String commandLine) throws ServiceException {
        String[] commandArr = commandLine.split(" ");
        for (int i = 0; i < commandArr.length; i++) {
            String cmd = commandArr[i];
            CommandExecutorRequest request = new CommandExecutorRequest();
            request.setCommand(cmd);
            request.setPosition(i);
            CommandExecutor commandExecutor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
            commandExecutor.execute(request);
        }
    }
}

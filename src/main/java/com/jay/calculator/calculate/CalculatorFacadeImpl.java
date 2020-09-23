package com.jay.calculator.calculate;

import com.jay.calculator.calculate.commandline.CommandLine;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.Service;

@Service()
public class CalculatorFacadeImpl extends AbstractCalculatorFacade implements CalculatorFacade {

    @Override
    protected void process(String command) throws ServiceException {
        CommandLine commandLine = this.getCommandLineByInput(command);
        commandLine.doCommand(command);
    }

    /**
     * get commandLine instance from context by the enum we get from input
     */
    private CommandLine getCommandLineByInput(String CommandLine) throws ServiceException {
        CommandLineEnum cmdLineEnum = this.getCommandLineEnum(CommandLine);
        return (CommandLine) ApplicationContext.getContext().get(cmdLineEnum.getCls());
    }


    /**
     * to identify the commandline
     * it the command is null, throw exception
     * if the command is some special ones which can match the ones defined in "CommandLineEnum" ,directly choose that enum.
     * if the command is not null and not match enum directly, then that will be CommandLineEnum.CALCULATE
     */
    private CommandLineEnum getCommandLineEnum(String commandLine) throws ServiceException {
        boolean commandIsNull = commandLine == null || commandLine.trim().length() == 0;
        if (commandIsNull) {
            throw new ServiceException(ErrorCodeEnum.ERROR_COMMANDLINE_EMPTY, "command line is empty");
        }
        // get command line by input. if there is some special input ,we can find the command line. if not we will do calculating
        CommandLineEnum cmdLine = CommandLineEnum.getByCommandLine(commandLine.trim());
        boolean existSpecialCmdLine = cmdLine != null;
        if (existSpecialCmdLine) {
            return cmdLine;
        } else {
            return CommandLineEnum.CALCULATE;
        }
    }

}

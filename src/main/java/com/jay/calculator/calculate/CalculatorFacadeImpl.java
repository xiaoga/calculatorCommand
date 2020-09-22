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
        CommandLine commandLine=this.getCommandLineByInput(command);
        commandLine.doCommand(command);

    }

    private CommandLine getCommandLineByInput(String CommandLine) throws ServiceException {
        CommandLineEnum cmdLineEnum = this.getCommandLineEnum(CommandLine);
        CommandLine commandLine = (CommandLine) ApplicationContext.getContext().get(cmdLineEnum.getCls());
        return commandLine;
    }

    private CommandLineEnum getCommandLineEnum(String CommandLine) throws ServiceException {
        boolean commandIsNull = CommandLine == null && CommandLine.trim().length() != 0;
        if (commandIsNull) {
            throw new ServiceException(ErrorCodeEnum.ERROR_COMMANDLINE_EMPTY, "command line is empty");
        }
        // get command line by input. if there is some special input ,we can find the command line. if not we will do calculating
        CommandLineEnum cmdLine = CommandLineEnum.getByCommandLine(CommandLine.trim());
        boolean existSpecialCmdLine = cmdLine != null;
        if (existSpecialCmdLine) {
            return cmdLine;
        } else {
            return CommandLineEnum.CALCULATE;
        }
    }

}

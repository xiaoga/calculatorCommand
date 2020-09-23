package com.jay.calculator.calculate.commandline;

import com.jay.calculator.common.exception.ManInfo;
import com.jay.calculator.container.bean.Service;

@Service()
@ManInfo(usage = "commandLine:'exit'. exit application")
public class CommandLineExit implements CommandLine {
    @Override
    public void doCommand(String commandLine) {
        System.exit(0);
    }
}

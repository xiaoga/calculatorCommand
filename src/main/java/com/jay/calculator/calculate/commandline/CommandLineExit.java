package com.jay.calculator.calculate.commandline;

import com.jay.calculator.container.bean.Service;

@Service()
public class CommandLineExit implements CommandLine {
    @Override
    public void doCommand(String commandLine) {
        System.exit(0);
    }
}

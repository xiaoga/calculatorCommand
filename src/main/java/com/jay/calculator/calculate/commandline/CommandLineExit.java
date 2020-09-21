package com.jay.calculator.calculate.commandline;

public class CommandLineExit implements CommandLine {
    @Override
    public void doCommand(String commandLine) {
        System.exit(0);
    }
}

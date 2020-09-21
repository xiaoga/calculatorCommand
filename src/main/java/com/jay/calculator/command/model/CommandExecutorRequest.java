package com.jay.calculator.command.model;

public class CommandExecutorRequest {

    String command;
    Integer position;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

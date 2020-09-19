package com.jay.calculator.service.command;

import com.jay.calculator.service.exception.ServiceException;

/**
 * @author jay
 * @comment it is the interface of commandExecutor.
 */
public interface CommandExecutor {
    /**
     * @param command .it is a String input.
     * @comment every input param will trigger command executing. this method will control the calculating logic
     */
    void execute(String command) throws ServiceException;
}

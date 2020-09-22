package com.jay.calculator.command;

import com.jay.calculator.common.exception.ServiceException;

/**
 * @author jay
 * it is the interface of commandExecutor.
 */
public interface CommandExecutor {
    /**
     * @param command .it is a CommandExecutorRequest with command and position inside.
     *                every input param will trigger command executing. this method will control the calculating logic
     */
    void execute(String command) throws ServiceException;
}

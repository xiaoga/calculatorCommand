package com.jay.calculator.command;

import com.jay.calculator.command.model.CommandExecutorRequest;
import com.jay.calculator.common.exception.ServiceException;

/**
 * @author jay
 * @comment it is the interface of commandExecutor.
 */
public interface CommandExecutor {
    /**
     * @param request .it is a CommandExecutorRequest with command and position inside.
     * @comment every input param will trigger command executing. this method will control the calculating logic
     */
    void execute(CommandExecutorRequest request) throws ServiceException;
}

package com.jay.calculator.service.command.cmd;

import com.jay.calculator.service.exception.ServiceException;

/**
 * @author jay
 * @comment it is the interface of command. each command will implement this interface
 */
public interface CalculateCommand {
    /**
     * @param
     * @return
     * @comment process command.if you want do add some operators ,you need to implement this method
     */
    void processCommand() throws ServiceException;
}

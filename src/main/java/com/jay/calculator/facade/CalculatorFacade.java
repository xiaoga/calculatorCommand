package com.jay.calculator.facade;

import com.jay.calculator.service.exception.ServiceException;

/**
 * @author jay
 * @comment this is the facade of calculator
*/
public interface CalculatorFacade {

    /**
     * to process command here
     * @param  command
     * @return void
    */
    void processCommand(String command) throws ServiceException;
}

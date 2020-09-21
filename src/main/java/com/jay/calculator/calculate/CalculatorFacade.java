package com.jay.calculator.calculate;

import com.jay.calculator.common.exception.ServiceException;

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

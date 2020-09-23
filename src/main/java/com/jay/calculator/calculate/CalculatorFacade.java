package com.jay.calculator.calculate;

import com.jay.calculator.common.exception.ServiceException;

/**
 * @author jay
 * this is the facade of calculator
*/
public interface CalculatorFacade {

    /**
     * to process commandline here
     * @param  commandLine it is the command line
    */
    void processCommand(String commandLine) throws ServiceException;
}

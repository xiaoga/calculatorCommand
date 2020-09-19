package com.jay.calculator.facade;

import com.jay.calculator.service.exception.ServiceException;

public abstract class AbstractCalculatorFacade implements CalculatorFacade {

    public void processCommand(String command) throws ServiceException {
        preProcess(command);
        process(command);
        postProcess(command);
    }

    protected void preProcess(String command){
        //System.out.println("do something before process command, we can log here");
    }
    protected void postProcess(String command){
        //System.out.println("do something after process command, we can log here");
    }
    protected abstract void process(String command) throws ServiceException;
}

package com.jay.test;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.facade.CalculatorFacade;
import com.jay.calculator.facade.CalculatorFacadeImpl;
import com.jay.calculator.service.command.CommandExecutor;
import com.jay.calculator.service.command.CommandExecutorImpl;
import com.jay.calculator.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class CalculatorFacadeImplTest {

    @Before
    public void initContext() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ApplicationContext.initContext();
        System.out.println(ApplicationContext.getContext());
    }

    @Test
    public void testSample(){
        String commandLine="1 2 13 4 + * -";
        CalculatorFacade calculatorFacade=(CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
        try {
            calculatorFacade.processCommand(commandLine);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }
}

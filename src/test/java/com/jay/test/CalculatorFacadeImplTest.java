package com.jay.test;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.facade.CalculatorFacade;
import com.jay.calculator.facade.CalculatorFacadeImpl;
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
    public void testSample() throws ServiceException {
        String commandLine = "1 2 13 4 + * -";
        runArray(commandLine);

    }

    @Test
    public void testClearAndUndo() {
        String commandLine = "1 2 13 4 + 5 8 clear";
        try {
            runArray(commandLine);
            System.out.println(ApplicationContext.getContextStack());
            commandLine = "undo";
            runArray(commandLine);
        } catch (ServiceException serviceException) {
            serviceException.printStackTrace();
        }
        System.out.println(ApplicationContext.getContextStack());
    }

    private void runArray(String line) throws ServiceException {
        String[] arr = line.split(" ");
        for (String cmd : arr) {
            CalculatorFacade calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
            calculatorFacade.processCommand(cmd);
        }
        System.out.println(ApplicationContext.getContextStack());
    }
}

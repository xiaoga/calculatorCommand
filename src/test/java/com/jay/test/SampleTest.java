package com.jay.test;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.facade.CalculatorFacade;
import com.jay.calculator.facade.CalculatorFacadeImpl;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class SampleTest {

    @Before
    public void prepare() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ApplicationContext.initContext();
    }

    @Test
    public void sample1() throws ServiceException {
        ApplicationContext.restStacks();
        System.out.println("======Example1=======");
        String line = "5 2";
        runArray(line);
    }

    @Test
    public void sample2() throws ServiceException {
        ApplicationContext.restStacks();
        System.out.println("======Example2=======");
        String line = "2 sqrt";
        runArray(line);
    }

    @Test
    public void sample3() throws ServiceException {
        ApplicationContext.restStacks();
        System.out.println("======Example3=======");
        String line = "5 2 -";
        runArray(line);
        line = "3 -";
        runArray(line);
    }

    @Test
    public void sample4() throws ServiceException {
        ApplicationContext.restStacks();
        System.out.println("======Example4=======");
        String line = "5 4 3 2";
        runArray(line);
        line = "undo undo *";
        runArray(line);
        line = "5 *";
        runArray(line);
        line = "undo";
        runArray(line);
    }


    @Test
    public void sample5() throws ServiceException {
        ApplicationContext.restStacks();
        System.out.println("======Example5=======");
        String line = "7 12 2 /";
        runArray(line);
        line = "*";
        runArray(line);
        line = "4 /";
        runArray(line);
    }

    @Test
    public void sample6() throws ServiceException {
        ApplicationContext.restStacks();
        System.out.println("======Example6=======");
        String line = "1 2 3 4 5";
        runArray(line);
        line = "*";
        runArray(line);
        line = "clear 3 4 -";
        runArray(line);
    }

    @Test
    public void sample7() throws ServiceException {
        ApplicationContext.restStacks();
        System.out.println("======Example7=======");
        String line = "1 2 3 4 5";
        runArray(line);
        line = "* * * *";
        runArray(line);
    }

    @Test
    public void sample8() {
        ApplicationContext.restStacks();
        System.out.println("======Example8=======");
        String line = "1 2 3 * 5 + * * 6 5";
        try {
            runArray(line);
        } catch (ServiceException serviceException) {
            System.out.println(serviceException.getErrorCodeEnum());
            Assert.assertEquals(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, serviceException.getErrorCodeEnum());
        }
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

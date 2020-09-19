package com.jay.test;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.CommandExecutor;
import com.jay.calculator.service.command.CommandExecutorImpl;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class CommandExecutorImplTest {
    @Before
    public void initContext() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ApplicationContext.initContext();

    }

    @Test
    public void testAddCommand() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException {
        CommandExecutor executor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
        String input1 = "11";
        String input2 = "22";
        String input3 = "+";
        executor.execute(input1);
        //  System.out.println(ApplicationContext.getContextStack());
        executor.execute(input1);
        //  System.out.println(ApplicationContext.getContextStack());
        executor.execute(input2);
        System.out.println(ApplicationContext.getContextStack());
        executor.execute(input3);
        //  System.out.println(ApplicationContext.getContextStack());

    }

    @Test
    public void testMinusCommand() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException {
        CommandExecutor executor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
        String clear = "clear";
        executor.execute(clear);

        String input1 = "11";
        String input2 = "22";
        String input3 = "-";
        executor.execute(input1);
        // System.out.println(ApplicationContext.getContextStack());
        executor.execute(input2);
        System.out.println(ApplicationContext.getContextStack());
        executor.execute(input3);
        //System.out.println(ApplicationContext.getContextStack());

    }


    @Test
    public void testDivideCommand() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException {
        CommandExecutor executor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
        String clear = "clear";
        executor.execute(clear);

        String input1 = "11";
        String input2 = "22";
        String input3 = "/";
        executor.execute(input1);
        //System.out.println(ApplicationContext.getContextStack());
        executor.execute(input2);
        System.out.println(ApplicationContext.getContextStack());
        executor.execute(input3);
        // System.out.println(ApplicationContext.getContextStack());

    }

    @Test
    public void testTimesCommand() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException {
        CommandExecutor executor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
        String clear = "clear";
        executor.execute(clear);

        String input1 = "11";
        String input2 = "22";
        String input3 = "*";
        executor.execute(input1);
        //  System.out.println(ApplicationContext.getContextStack());
        executor.execute(input2);
        System.out.println(ApplicationContext.getContextStack());
        executor.execute(input3);
        //  System.out.println(ApplicationContext.getContextStack());

    }

    @Test
    public void testDivideZero() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        CommandExecutor executor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
        try {
            String clear = "clear";
            executor.execute(clear);
            String input1 = "11";
            String input2 = "0";
            String input3 = "/";
            executor.execute(input1);
            //  System.out.println(ApplicationContext.getContextStack());
            executor.execute(input2);
            System.out.println(ApplicationContext.getContextStack());
            executor.execute(input3);
        } catch (ServiceException e) {
            System.out.println("exception: "+e.getErrorCodeEnum());
            Assert.assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DENOMINATOR_IS_ZERO);
        }
        //  System.out.println(ApplicationContext.getContextStack());

    }
}

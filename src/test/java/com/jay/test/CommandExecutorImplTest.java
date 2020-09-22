package com.jay.test;

import com.jay.calculator.command.CommandExecutor;
import com.jay.calculator.command.CommandExecutorImpl;
import com.jay.calculator.command.dal.DataDao;
import com.jay.calculator.command.dal.DataDaoImpl;
import com.jay.calculator.command.model.CommandExecutorRequest;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class CommandExecutorImplTest extends BaseTest {
    private DataDao dataDao;
    private CommandExecutor executor;

    @Before
    public void initContext() {

        dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        executor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);

    }

    @Test
    public void testAddCommand() throws ServiceException {
        resetStacks();

        String input1 = "11";
        String input2 = "22";
        String input3 = "+";

        executor.execute(input1);
        executor.execute(input2);
        System.out.println("stack:" + dataDao.getStack());
        executor.execute(input3);

    }

    @Test
    public void testMinusCommand() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException {
        resetStacks();

        String input1 = "11";
        String input2 = "22";
        String input3 = "-";
        CommandExecutorRequest request1 = new CommandExecutorRequest();
        request1.setPosition(0);
        request1.setCommand(input1);

        executor.execute(input1);
        executor.execute(input2);
        System.out.println("stack:" + dataDao.getStack());
        executor.execute(input3);

    }


    @Test
    public void testDivideCommand() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException {
        resetStacks();

        String input1 = "11";
        String input2 = "22";
        String input3 = "/";

        executor.execute(input1);
        executor.execute(input2);
        System.out.println("stack:" + dataDao.getStack());
        executor.execute(input3);

    }

    @Test
    public void testTimesCommand() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ServiceException {
        resetStacks();

        String input1 = "11";
        String input2 = "22";
        String input3 = "*";
        executor.execute(input1);
        executor.execute(input2);
        System.out.println("stack:" + dataDao.getStack());
        executor.execute(input3);

    }

    @Test
    public void testDivideZero() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        resetStacks();
        try {

            String input1 = "11";
            String input2 = "0";
            String input3 = "/";
            executor.execute(input1);
            executor.execute(input2);
            System.out.println("stack:" + dataDao.getStack());
            executor.execute(input3);
        } catch (ServiceException e) {
            System.out.println("exception: " + e.getErrorCodeEnum());
            Assert.assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DENOMINATOR_IS_ZERO);
        }
        //  System.out.println(ApplicationContext.getContextStack());

    }

    private void resetStacks() {
        dataDao.resetStack();
        dataDao.resetUndoStack();
        ;
    }
}

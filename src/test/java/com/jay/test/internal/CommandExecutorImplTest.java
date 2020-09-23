package com.jay.test.internal;

import com.jay.calculator.command.CommandExecutor;
import com.jay.calculator.command.CommandExecutorImpl;
import com.jay.calculator.command.dal.DataDao;
import com.jay.calculator.command.dal.DataDaoImpl;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.test.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void testMinusCommand() throws ServiceException {
        resetStacks();
        String input1 = "11";
        String input2 = "22";
        String input3 = "-";
        executor.execute(input1);
        executor.execute(input2);
        System.out.println("stack:" + dataDao.getStack());
        executor.execute(input3);

    }


    @Test
    public void testDivideCommand() throws ServiceException {
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
    public void testTimesCommand() throws ServiceException {
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
    public void testDivideZero() {
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
    }

    private void resetStacks() {
        dataDao.resetStack();
        dataDao.resetUndoStack();
    }
}

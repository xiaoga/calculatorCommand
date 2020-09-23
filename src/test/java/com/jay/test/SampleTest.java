package com.jay.test;

import com.jay.calculator.calculate.CalculatorFacade;
import com.jay.calculator.calculate.CalculatorFacadeImpl;
import com.jay.calculator.command.CommandExecutor;
import com.jay.calculator.command.CommandExecutorImpl;
import com.jay.calculator.command.CommandQueryService;
import com.jay.calculator.command.CommandQueryServiceImpl;
import com.jay.calculator.command.dal.DataDao;
import com.jay.calculator.command.dal.DataDaoImpl;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SampleTest extends BaseTest {
    private DataDao dataDao;
    private CommandExecutor executor;
    private CommandQueryService commandQueryService;

    @Before
    public void initContext() {
        dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        executor = (CommandExecutor) ApplicationContext.getContext().get(CommandExecutorImpl.class);
        commandQueryService = (CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
    }

    @Test
    public void sample1() throws ServiceException {
        resetStacks();
        System.out.println("======Example1=======");
        String line = "5 2";
        runArray(line);
        Assert.assertEquals("5 2",commandQueryService.queryStack());
    }

    @Test
    public void sample2() throws ServiceException {
        resetStacks();
        System.out.println("======Example2=======");
        String line = "2 sqrt";
        runArray(line);
        Assert.assertEquals("1.4142135623",commandQueryService.queryStack());
    }

    @Test
    public void sample3() throws ServiceException {
        resetStacks();
        System.out.println("======Example3=======");
        String line = "5 2 -";
        runArray(line);
        Assert.assertEquals("3",commandQueryService.queryStack());

        line = "3 -";
        runArray(line);
        Assert.assertEquals("0",commandQueryService.queryStack());

        line = "clear";
        runArray(line);
        Assert.assertEquals("",commandQueryService.queryStack());
    }

    @Test
    public void sample4() throws ServiceException {
        resetStacks();
        System.out.println("======Example4=======");
        String line = "5 4 3 2";
        runArray(line);
        Assert.assertEquals("5 4 3 2",commandQueryService.queryStack());

        line = "undo undo *";
        runArray(line);
        Assert.assertEquals("20",commandQueryService.queryStack());

        line = "5 *";
        runArray(line);
        Assert.assertEquals("100",commandQueryService.queryStack());

        line = "undo";
        runArray(line);
        Assert.assertEquals("20 5",commandQueryService.queryStack());
    }


    @Test
    public void sample5() throws ServiceException {
        resetStacks();
        System.out.println("======Example5=======");
        String line = "7 12 2 /";
        runArray(line);
        Assert.assertEquals("7 6",commandQueryService.queryStack());

        line = "*";
        runArray(line);
        Assert.assertEquals("42",commandQueryService.queryStack());

        line = "4 /";
        runArray(line);
        Assert.assertEquals("10.5",commandQueryService.queryStack());
    }

    @Test
    public void sample6() throws ServiceException {
        resetStacks();
        System.out.println("======Example6=======");
        String line = "1 2 3 4 5";
        runArray(line);
        Assert.assertEquals("1 2 3 4 5",commandQueryService.queryStack());

        line = "*";
        runArray(line);
        Assert.assertEquals("1 2 3 20",commandQueryService.queryStack());

        line = "clear 3 4 -";
        runArray(line);
        Assert.assertEquals("-1",commandQueryService.queryStack());
    }

    @Test
    public void sample7() throws ServiceException {
        resetStacks();
        System.out.println("======Example7=======");
        String line = "1 2 3 4 5";
        runArray(line);
        Assert.assertEquals("1 2 3 4 5",commandQueryService.queryStack());

        line = "* * * *";
        runArray(line);
        Assert.assertEquals("120",commandQueryService.queryStack());
    }

    @Test
    public void sample8() throws ServiceException {
        resetStacks();
        System.out.println("======Example8=======");
        String line = "1 2 3 * 5 + * * 6 5";
        try {
            runArray(line);
        } catch (ServiceException serviceException) {
            System.out.println(serviceException.getErrorCodeEnum());
            Assert.assertEquals(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, serviceException.getErrorCodeEnum());
        }
        Assert.assertEquals("11",commandQueryService.queryStack());
    }

    private void runArray(String line) throws ServiceException {
        System.out.println(line);
        CalculatorFacade calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
        calculatorFacade.processCommand(line);
        System.out.println("stack:" + commandQueryService.queryStack());
    }

    private void resetStacks() {
        dataDao.resetStack();
        dataDao.resetUndoStack();
    }

}

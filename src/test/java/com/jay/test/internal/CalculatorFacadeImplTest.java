package com.jay.test.internal;

import com.jay.calculator.calculate.CalculatorFacade;
import com.jay.calculator.calculate.CalculatorFacadeImpl;
import com.jay.calculator.command.CommandQueryService;
import com.jay.calculator.command.CommandQueryServiceImpl;
import com.jay.calculator.command.dal.DataDao;
import com.jay.calculator.command.dal.DataDaoImpl;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.test.BaseTest;
import org.junit.Before;
import org.junit.Test;

public class CalculatorFacadeImplTest extends BaseTest {
    private CommandQueryService commandQueryService;
    private DataDao dataDao;

    @Before
    public void initContext() {
        System.out.println(ApplicationContext.getContext());
        commandQueryService = (CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
        dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);

    }

    @Test
    public void testSample() throws ServiceException {
        dataDao.resetStack();
        dataDao.resetUndoStack();
        String commandLine = "1 2 13 4 + * -";
        runArray(commandLine);
        System.out.println(dataDao.getStack());
    }

    @Test
    public void testClearAndUndo() throws ServiceException {
        dataDao.resetStack();
        dataDao.resetUndoStack();
        String commandLine = "1 2 13 4 + 5 8 clear";
        try {
            runArray(commandLine);
            System.out.println("stack:" + commandQueryService.queryStack());
            commandLine = "undo";
            runArray(commandLine);
        } catch (ServiceException serviceException) {
            serviceException.printStackTrace();
        }
        System.out.println("stack:" + commandQueryService.queryStack());
    }

    private void runArray(String line) throws ServiceException {
        CalculatorFacade calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
        calculatorFacade.processCommand(line);
    }

    private void resetStacks() {
        dataDao.resetStack();
        dataDao.resetUndoStack();
    }
}

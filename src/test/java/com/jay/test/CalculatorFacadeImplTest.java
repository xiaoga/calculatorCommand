package com.jay.test;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.facade.CalculatorFacade;
import com.jay.calculator.facade.CalculatorFacadeImpl;
import com.jay.calculator.service.command.CommandQueryService;
import com.jay.calculator.service.command.CommandQueryServiceImpl;
import com.jay.calculator.service.command.dal.DataDao;
import com.jay.calculator.service.command.dal.DataDaoImpl;
import com.jay.calculator.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class CalculatorFacadeImplTest {
    private CommandQueryService commandQueryService;
    private DataDao dataDao;

    @Before
    public void initContext() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ApplicationContext.initContext();
        System.out.println(ApplicationContext.getContext());
        commandQueryService = (CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
        dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);

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
            System.out.println("stack:" + commandQueryService.queryStack());
            commandLine = "undo";
            runArray(commandLine);
        } catch (ServiceException serviceException) {
            serviceException.printStackTrace();
        }
        System.out.println("stack:" + commandQueryService.queryStack());
    }

    private void runArray(String line) throws ServiceException {
        String[] arr = line.split(" ");
        for (String cmd : arr) {
            CalculatorFacade calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
            calculatorFacade.processCommand(cmd);
        }
        System.out.println("stack:" + commandQueryService.queryStack());
    }

    private void resetStacks() {
        dataDao.resetStack();
        dataDao.resetUndoStack();
        ;
    }
}

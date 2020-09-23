package com.jay.test;

import com.jay.calculator.calculate.CalculatorFacade;
import com.jay.calculator.calculate.CalculatorFacadeImpl;
import com.jay.calculator.command.CommandQueryService;
import com.jay.calculator.command.CommandQueryServiceImpl;
import com.jay.calculator.command.dal.DataDao;
import com.jay.calculator.command.dal.DataDaoImpl;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.Service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Service
public class OtherCasesTest extends BaseTest {
    private CalculatorFacade calculatorFacade;
    private CommandQueryService commandQueryService;
    private DataDao dataDao;

    @Before
    public void initContext() {
        commandQueryService = (CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
        dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
    }

    @Test
    public void testMan() throws ServiceException {
        resetStacks();
        String line = "man";
        calculatorFacade.processCommand(line);
    }

    @Test
    public void testEmptyInput() throws ServiceException {
        resetStacks();
        String line = " ";
        try {
            calculatorFacade.processCommand(line);
        } catch (ServiceException serviceException) {
            Assert.assertEquals(ErrorCodeEnum.ERROR_COMMANDLINE_EMPTY, serviceException.getErrorCodeEnum());
        }
    }

    private void resetStacks() {
        dataDao.resetStack();
        dataDao.resetUndoStack();
    }
}


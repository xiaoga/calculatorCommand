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
public class SpecialCasesTest extends BaseTest {
    private CalculatorFacade calculatorFacade;
    private CommandQueryService commandQueryService;
    private DataDao dataDao;

    @Before
    public void initContext() {
        commandQueryService = (CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
        dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
    }

    /*if we do not set scale and round type for divide it will occur some issues*/
    @Test
    public void test4Divide3() throws ServiceException {
        resetStacks();
        String line = "4 3 /";
        calculatorFacade.processCommand(line);
        Assert.assertEquals("1.3333333333", commandQueryService.queryStack());
    }

    @Test
    public void insufficientParam() throws ServiceException {
        resetStacks();
        String line = "4 /";
        try {
            calculatorFacade.processCommand(line);
        } catch (ServiceException e) {
            Assert.assertEquals(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, e.getErrorCodeEnum());
        }
        Assert.assertEquals("4", commandQueryService.queryStack());
    }

    @Test
    public void denominatorZero() throws ServiceException {
        resetStacks();
        String line = "4 0 /";
        try {
            calculatorFacade.processCommand(line);
        } catch (ServiceException e) {
            Assert.assertEquals(ErrorCodeEnum.ERROR_DENOMINATOR_IS_ZERO, e.getErrorCodeEnum());
        }
        Assert.assertEquals("4 0", commandQueryService.queryStack());
    }

    @Test
    public void inputLong() throws ServiceException {
        resetStacks();
        String line = "4.123456789012345678";
        calculatorFacade.processCommand(line);
        Assert.assertEquals("4.1234567890", commandQueryService.queryStack());
    }

    @Test
    public void inputNotNumberAndCommandLine() {
        resetStacks();
        String line = "xxxx";
        try {
            calculatorFacade.processCommand(line);
        } catch (ServiceException e) {
            Assert.assertEquals(ErrorCodeEnum.ERROR_PARAM_IS_NOT_NUMBER, e.getErrorCodeEnum());
        }
    }

    private void resetStacks() {
        dataDao.resetStack();
        dataDao.resetUndoStack();
    }
}


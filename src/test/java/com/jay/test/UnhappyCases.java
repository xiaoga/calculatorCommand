package com.jay.test;

import com.jay.calculator.calculate.CalculatorFacade;
import com.jay.calculator.calculate.CalculatorFacadeImpl;
import com.jay.calculator.command.CommandQueryService;
import com.jay.calculator.command.CommandQueryServiceImpl;
import com.jay.calculator.command.dal.DataDao;
import com.jay.calculator.command.dal.DataDaoImpl;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.BeanFactory;
import com.jay.calculator.container.bean.Service;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Service
public class UnhappyCases {
    private CalculatorFacade calculatorFacade;
    private CommandQueryService commandQueryService;
    private DataDao dataDao;

    @Before
    public void initContext() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        // ApplicationContext.initContext();
        BeanFactory.initBean();
        System.out.println(ApplicationContext.getContext());
        commandQueryService = (CommandQueryService) ApplicationContext.getContext().get(CommandQueryServiceImpl.class);
        dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);

    }

    /*if we do not set scale and round type for divide it will occur some issues*/
    @Test
    public void test4Divide3() throws ServiceException {
        String line = "4 3 /";
        calculatorFacade.processCommand(line);
        System.out.println(commandQueryService.queryStack());
    }

    private void resetStacks() {
        dataDao.resetStack();
        dataDao.resetUndoStack();
    }

    private void runArray(String line) throws ServiceException {
        CalculatorFacade calculatorFacade = (CalculatorFacade) ApplicationContext.getContext().get(CalculatorFacadeImpl.class);
        calculatorFacade.processCommand(line);
        // System.out.println("stack:" + commandQueryService.queryStack());
    }

}


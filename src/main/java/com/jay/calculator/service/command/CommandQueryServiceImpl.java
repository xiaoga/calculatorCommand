package com.jay.calculator.service.command;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.dal.DataDao;
import com.jay.calculator.service.command.dal.DataDaoImpl;

import java.util.Stack;

public class CommandQueryServiceImpl implements CommandQueryService {
    @Override
    public Stack<String> queryStack() {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        return dataDao.getStack();
    }
}

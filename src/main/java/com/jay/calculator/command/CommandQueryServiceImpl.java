package com.jay.calculator.command;

import com.jay.calculator.command.dal.DataDao;
import com.jay.calculator.command.dal.DataDaoImpl;
import com.jay.calculator.command.model.CommandNumber;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.Service;

@Service
public class CommandQueryServiceImpl implements CommandQueryService {

    //[requirement]
    // 1. query stack get data split by space
    // 2. show 10 number right side “.”
    @Override
    public String queryStack() throws ServiceException {
        DataDao dataDao = (DataDao) ApplicationContext.getContext().get(DataDaoImpl.class);
        int size = dataDao.getStack().size();
        String array[] = new String[size];

        // deal with data in stack
        for (int i = 0; i < size; i++) {
            String element = dataDao.getStack().get(i);
            CommandNumber number = new CommandNumber(element);
            array[i] = number.getBigDecimal10().toString();
        }

        return printArray(array);
    }

    public String printArray(String[] array) {
        StringBuffer sb = new StringBuffer();
        for (String str : array
        ) {
            sb.append(str + " ");
        }
        String rst = sb.toString().trim();
        return rst;
    }
}

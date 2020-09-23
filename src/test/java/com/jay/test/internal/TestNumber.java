package com.jay.test.internal;

import com.jay.calculator.command.model.CommandNumber;
import com.jay.calculator.common.exception.ServiceException;
import org.junit.Test;

public class TestNumber {

    @Test
    public void TestCommandNumber() throws ServiceException {
        String number ="123.333333333333300000";
        CommandNumber num=new CommandNumber(number);
        System.out.println(num);
    }
}

package com.jay.test;

import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.command.OperatorCommandEnum;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class ApplicationContextTest {

    @Test
    public void testEnumSucc() {
        Assert.assertNotNull(OperatorCommandEnum.getByOperator("+"));
    }
    @Test
    public void testEnumNull() {
        Assert.assertNull(OperatorCommandEnum.getByOperator("x"));
    }

    @Test
    public void testApplicationContextInit() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ApplicationContext.initContext();
        System.out.println(ApplicationContext.getContext());
        Assert.assertNotNull(ApplicationContext.getContext());
    }
}

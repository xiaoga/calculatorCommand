package com.jay.test;

import com.jay.calculator.command.OperatorCommandEnum;
import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.FilePathClassLoader;
import com.jay.calculator.container.bean.BeanFactory;
import com.jay.test.application.TestContainerStarter;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class testContainerStarterTest extends BaseTest{

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
        TestContainerStarter.initContext();
        System.out.println(ApplicationContext.getContext());
        Assert.assertNotNull(ApplicationContext.getContext());
    }

    @Test
    public void testGetClasses() throws IOException {
        FilePathClassLoader.getFileList();
    }

    @Test
    public  void testInitBean() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        BeanFactory.initBean();
        System.out.println("ctx is"+ ApplicationContext.getContext());

    }
}

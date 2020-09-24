package com.jay.test;

import com.jay.test.application.TestContainerStarter;
import org.junit.Before;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class BaseTest {
    @Before
    public void startContext() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        TestContainerStarter.initContext();
    }
}

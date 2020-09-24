package com.jay.test.application;

import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.bean.BeanFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TestContainerStarter {

    /**
     * init method to init context
     */
    public static void initContext() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        registerBeanIntoContainer();
        BeanFactory.initBean();
    }

    /**
     * get beans defined in configuration ,create instances and put them into context
     */
    private static void registerBeanIntoContainer() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Class> classList = getBeanListFromConfig();
        for (Class cls : classList) {
            ApplicationContext.getContext().put(cls, createBeanInstance(cls));
        }
    }

    private static Object createBeanInstance(Class cls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return cls.getConstructor().newInstance();
    }

    private static List<Class> getBeanListFromConfig() {
        List<Class> beanList = new ArrayList<>();
        RegisterBeanEnum[] registerBeanEnum = RegisterBeanEnum.values();
        for (RegisterBeanEnum en : registerBeanEnum) {
            beanList.add(en.getCls());
        }
        return beanList;
    }
}

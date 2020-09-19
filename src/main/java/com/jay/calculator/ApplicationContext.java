package com.jay.calculator;

import com.jay.calculator.service.command.model.UndoBean;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationContext {

    private static Map<Class, Object> context = new HashMap<Class, Object>();

    private static Stack<String> contextStack = new Stack();

    private static Stack<UndoBean> undoStack = new Stack();

    private static Map<String, Class> operatorCommandMap = new HashMap<String, Class>();

    public static Stack<String> getContextStack() {
        return contextStack;
    }

    public static void setContextStack(Stack contextStack) {
        ApplicationContext.contextStack = contextStack;
    }

    public static Stack<UndoBean> getUndoStack() {
        return undoStack;
    }

    public static void setUndoStack(Stack<UndoBean> undoStack) {
        ApplicationContext.undoStack = undoStack;
    }

    public static Map<Class, Object> getContext() {
        return context;
    }

    public static void setContext(Map<Class, Object> context) {
        ApplicationContext.context = context;
    }

    public static void initContext() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        registerBeanIntoContainer();
    }

    /**
     * once a new service is needed you need to register it here to ensure it can be gotten from application context
     */
    private static void registerBeanIntoContainer() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Class> classList = getBeanListFromConfig();
        for (Class cls : classList) {
            context.put(cls, createBeanInstance(cls));
        }

    }

    private static Object createBeanInstance(Class cls) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return cls.getConstructor().newInstance();
    }

    private static List<Class> getBeanListFromConfig() {
        List<Class> beanList = new ArrayList<Class>();
        RegisterBeanEnum[] registerBeanEnum = RegisterBeanEnum.values();
        for (RegisterBeanEnum en : registerBeanEnum) {
            beanList.add(en.getCls());
        }
        return beanList;
    }
}

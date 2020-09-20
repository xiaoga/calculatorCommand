package com.jay.calculator;

import com.jay.calculator.service.command.model.UndoBean;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ApplicationContext {

    /**
     * application context here, all the instances of bean will be saved here
    */
    private static Map<Class, Object> context = new HashMap<Class, Object>();

    /**
     * stack to save element and operators
     */
    private static Stack<String> contextStack = new Stack();

    /**
     * stack to save all the operations so that it can be used to do "UNDO" operation
     */
    private static Stack<UndoBean> undoStack = new Stack();

    /*setters and getters*/
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



    /**
     * init method to init context
     */
    public static void initContext() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        registerBeanIntoContainer();
    }

    /**
     * get beans defined in configuration ,create instances and put them into context
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

    public static void restStacks() {
        contextStack.removeAllElements();
        undoStack.removeAllElements();
    }
}

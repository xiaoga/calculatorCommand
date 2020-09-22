package com.jay.calculator.container;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    /**
     * application context here, all the instances of bean will be saved here
     */
    private static Map<Class<?>, Object> context = new HashMap<Class<?>, Object>();


    /*setters and getters*/
    public static Map<Class<?>, Object> getContext() {
        return context;
    }

    public static void setContext(Map<Class<?>, Object> context) {
        ApplicationContext.context = context;
    }

}

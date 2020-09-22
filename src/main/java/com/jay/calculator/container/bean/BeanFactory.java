package com.jay.calculator.container.bean;

import com.jay.calculator.container.ApplicationContext;
import com.jay.calculator.container.FilePathClassLoader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BeanFactory {

    public static final String CLASS_POSTFIX = ".class";

    // init beans which have "@Service" annotation and put them into applicationContext
    public static void initBean() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        List<Class<?>> classList = getClassListFromFileSystem();
        for (Class<?> cls: classList) {
            initBeanWithServiceAnnotation(cls);
        }
    }

    private static void initBeanWithServiceAnnotation(Class<?> cls) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Service serviceAnnotation = cls.getAnnotation(Service.class);
        boolean existServiceAnnotation = serviceAnnotation != null;
        if (existServiceAnnotation) {
            Object bean = createInstance(cls);
            ApplicationContext.getContext().put(cls, bean);
        }
    }

    private static Object createInstance(Class<?> cls) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return cls.getConstructor().newInstance();
    }

    private static List<Class<?>> getClassListFromFileSystem() throws ClassNotFoundException, IOException {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        List<String> fileList = FilePathClassLoader.getFileList();
        convertPathListToClassList(fileList, classList);
        return classList;
    }

    private static void convertPathListToClassList(List<String> pathList, List<Class<?>> classList) throws ClassNotFoundException {
        for (String fileName : pathList) {
            boolean isClassFile = fileName.endsWith(CLASS_POSTFIX);
            if (isClassFile) dealWithClassFile(fileName, classList);
        }
    }

    private static void dealWithClassFile(String fileName, List<Class<?>> classList) throws ClassNotFoundException {
        String className = fileName.substring(0, fileName.indexOf(CLASS_POSTFIX));
        Class<?> cls = Class.forName(className);
        classList.add(cls);
    }
}

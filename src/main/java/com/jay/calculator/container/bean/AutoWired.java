package com.jay.calculator.container.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jay
 * this is the autoWire annotation. with this annotation, we can do auto wire without manually get bean in services
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWired {

    // this is the id of class which need to be wired. when interface of super class are the same we will use this property
    String id() default "";

    // this is the detail class of the bean which you want to inject, which should match the key in applicationContext
    Class<?> type();
}

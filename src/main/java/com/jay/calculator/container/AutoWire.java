package com.jay.calculator.container;

/**
 * @author jay
 * this is the autoWire annotation. with this annotation, we can do auto wire without manually get bean in services
 */
public @interface AutoWire {

    // this is the type of class which need to be wired
    Class<?> type();

    // this is the id of class which need to be wired. when interface of super class are the same we will use this property
    String id();
}

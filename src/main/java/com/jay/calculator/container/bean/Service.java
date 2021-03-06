package com.jay.calculator.container.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jay
 * this is the Server annotation. this annotation will let system know current class need to create instance
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    // this is the id of class which need to be wired. when interface of super class are the same we will use this property
    String id() default "";
}

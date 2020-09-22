package com.jay.calculator.command;

import com.jay.calculator.command.OperatorCommandEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jay
 * this is the annotation to show man info
 * */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ManInfo {

    // this is the command
    OperatorCommandEnum command();
    // this is how the command should be used
    String usage();
}

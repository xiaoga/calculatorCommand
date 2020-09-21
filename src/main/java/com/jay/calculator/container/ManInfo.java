package com.jay.calculator.container;

import com.jay.calculator.command.OperatorCommandEnum;

/**
 * @author Jay
 * this is the annotation to show man info
 * */
public @interface ManInfo {

    // this is the command
    OperatorCommandEnum command();
    // this is how the command should be used
    String usage();
}

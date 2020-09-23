package com.jay.calculator.command.dal;

import java.util.Stack;

public class UndoBean {

    private Stack<String> resultOutStack = new Stack<>();
    private Stack<String> resultInStack = new Stack<>();

    public Stack<String> getResultOutStack() {
        return resultOutStack;
    }

    public Stack<String> getResultInStack() {
        return resultInStack;
    }
}

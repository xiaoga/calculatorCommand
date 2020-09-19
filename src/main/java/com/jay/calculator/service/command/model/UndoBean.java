package com.jay.calculator.service.command.model;

import java.util.Stack;

public class UndoBean {

    private Stack<String> resultOutStack=new Stack<>();
    private Stack<String> resultInStack=new Stack<>();

    public Stack<String> getResultOutStack() {
        return resultOutStack;
    }

    public void setResultOutStack(Stack<String> resultOutStack) {
        this.resultOutStack = resultOutStack;
    }

    public Stack<String> getResultInStack() {
        return resultInStack;
    }

    public void setResultInStack(Stack<String> resultInStack) {
        this.resultInStack = resultInStack;
    }
}

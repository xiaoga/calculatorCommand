package com.jay.calculator.service.command.dal;

import java.util.Stack;

public class DataContext {
    /**
     * stack to save element and operators
     */
    private static Stack<String> contextStack = new Stack<>();

    /**
     * stack to save all the operations so that it can be used to do "UNDO" operation
     */
    private static Stack<UndoBean> undoStack = new Stack<>();

    /*setters and getters*/
    public static Stack<String> getContextStack() {
        return contextStack;
    }

    public static void setContextStack(Stack<String> contextStack) {
        DataContext.contextStack = contextStack;
    }

    public static Stack<UndoBean> getUndoStack() {
        return undoStack;
    }

    public static void setUndoStack(Stack<UndoBean> undoStack) {
        DataContext.undoStack = undoStack;
    }
}

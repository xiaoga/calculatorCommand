package com.jay.calculator.command.dal;

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

    /* getters*/
    public static Stack<String> getContextStack() {
        return contextStack;
    }

    public static Stack<UndoBean> getUndoStack() {
        return undoStack;
    }
}

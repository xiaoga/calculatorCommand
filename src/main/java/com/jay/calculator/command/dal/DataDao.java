package com.jay.calculator.command.dal;

import com.jay.calculator.common.exception.ServiceException;

import java.util.Stack;

public interface DataDao {

    String popFromStack() throws ServiceException;

    void pushStack(String element) throws ServiceException;

    UndoBean popFromUndoStack() throws ServiceException;

    void pushUndoStack(UndoBean undoBean);

    boolean stackIsEmpty();

    boolean undoStackIsEmpty();

    void resetStack();

    void resetUndoStack();

    Stack<String> getStack();
}

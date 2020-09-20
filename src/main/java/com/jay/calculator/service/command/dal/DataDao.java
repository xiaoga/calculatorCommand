package com.jay.calculator.service.command.dal;

import com.jay.calculator.service.exception.ServiceException;

import java.util.Stack;

public interface DataDao {

    String popFromStack() throws ServiceException;

    void pushStack(String element);

    UndoBean popFromUndoStack() throws ServiceException;

    void pushUndoStack(UndoBean undoBean);

    boolean stackIsEmpty();

    boolean undoStackIsEmpty();

    void resetStack();

    void resetUndoStack();

    Stack getStack();
}

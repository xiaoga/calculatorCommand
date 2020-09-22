package com.jay.calculator.command.dal;

import com.jay.calculator.command.model.CommandNumber;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.bean.Service;

import java.util.Stack;

@Service
public class DataDaoImpl implements DataDao {
    @Override
    public String popFromStack() throws ServiceException {
        boolean stackNull = DataContext.getContextStack().empty();
        if (!stackNull) {
            return DataContext.getContextStack().pop();
        } else {
            throw new ServiceException(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, "insufficient numbers in ContextStack! ");
        }
    }

    @Override
    public void pushStack(String element) throws ServiceException {
        CommandNumber commandNumber=new CommandNumber(element);
        DataContext.getContextStack().push(commandNumber.getBigDecimal15().toString());
    }

    @Override
    public UndoBean popFromUndoStack() throws ServiceException {
        boolean stackNull = DataContext.getUndoStack().empty();
        if (!stackNull) {
            return DataContext.getUndoStack().pop();
        } else {
            throw new ServiceException(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, "insufficient numbers in UndoStack! ");
        }
    }

    @Override
    public void pushUndoStack(UndoBean undoBean) {
        DataContext.getUndoStack().push(undoBean);
    }

    @Override
    public boolean stackIsEmpty() {
        return DataContext.getContextStack().isEmpty();
    }

    @Override
    public boolean undoStackIsEmpty() {
        return DataContext.getUndoStack().isEmpty();
    }

    @Override
    public void resetStack() {
        DataContext.getContextStack().removeAllElements();
    }

    @Override
    public void resetUndoStack() {
        DataContext.getUndoStack().removeAllElements();
    }

    @Override
    public Stack<String> getStack() {
        return DataContext.getContextStack();
    }


}

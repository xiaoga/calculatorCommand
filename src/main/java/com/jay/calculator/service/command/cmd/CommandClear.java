package com.jay.calculator.service.command.cmd;

import com.jay.calculator.service.command.dal.UndoBean;
import com.jay.calculator.service.exception.ServiceException;

public class CommandClear extends CalculateCommandBase implements CalculateCommand{
    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean=new UndoBean();
        while (!contextStackIsEmpty()){
            this.getSingleParamFromStack(undoBean);
        }
        this.setResultIn(undoBean,null);
    }
}

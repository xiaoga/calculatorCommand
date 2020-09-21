package com.jay.calculator.command.cmd;

import com.jay.calculator.command.OperatorCommandEnum;
import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.ManInfo;

@ManInfo(command= OperatorCommandEnum.CLEAR,usage = "'clear' sample: clear all the element in stack")
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

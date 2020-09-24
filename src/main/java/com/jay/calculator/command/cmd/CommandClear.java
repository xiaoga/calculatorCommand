package com.jay.calculator.command.cmd;

import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.common.exception.ManInfo;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.bean.AutoWired;
import com.jay.calculator.container.bean.Service;

@Service()
@ManInfo(usage = "command:'clear' sample: clear all the element in stack")
public class CommandClear implements CalculateCommand {

    @AutoWired(type = CalculateCommandBase.class)
    CalculateCommandBase calculateCommandBase;

    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean = new UndoBean();
        while (!calculateCommandBase.contextStackIsEmpty()) {
            calculateCommandBase.getSingleParamFromStack(undoBean);
        }
        calculateCommandBase.setResultIn(undoBean, null);
    }
}

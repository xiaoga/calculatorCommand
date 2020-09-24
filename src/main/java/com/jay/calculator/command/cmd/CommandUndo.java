package com.jay.calculator.command.cmd;

import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.common.exception.ManInfo;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.bean.AutoWired;
import com.jay.calculator.container.bean.Service;

@Service()
@ManInfo(usage = "command:'undo'. undo the last operate you did")
public class CommandUndo implements CalculateCommand {
    @AutoWired(type = CalculateCommandBase.class)
    CalculateCommandBase calculateCommandBase;

    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean = calculateCommandBase.getUndoBeanFromUndoStack();
        calculateCommandBase.takeOutResult(undoBean);
        calculateCommandBase.recover(undoBean);
    }


}

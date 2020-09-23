package com.jay.calculator.command.cmd;

import com.jay.calculator.command.OperatorCommandEnum;
import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.common.exception.ManInfo;
import com.jay.calculator.container.bean.Service;

@Service()
@ManInfo(usage = "command:'undo'. undo the last operate you did")
public class CommandUndo extends CalculateCommandBase implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean = this.getUndoBeanFromUndoStack();
        this.takeOutResult(undoBean);
        this.recover(undoBean);
    }


}

package com.jay.calculator.service.command.cmd;

import com.jay.calculator.service.command.dal.UndoBean;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;

public class CommandUndo extends CalculateCommandBase implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean = this.getUndoBeanFromUndoStack();
        this.takeOutResult(undoBean);
        this.recover(undoBean);
    }

    private void takeOutResult(UndoBean undoBean) throws ServiceException {
        while (!undoBean.getResultInStack().isEmpty()) {
            boolean hasElementInStack = !this.contextStackIsEmpty();
            if (hasElementInStack) {
                String inStr = undoBean.getResultInStack().pop();
                String rst = this.popFromStack();
                boolean undoInfoMatch = inStr.equals(rst);
                if (!undoInfoMatch) {
                    throw new ServiceException(ErrorCodeEnum.ERROR_UNDO_INFO_MISTMATCH, "undo param is:[" + inStr + "],stack param is:[" + rst + "]");
                }
            }
        }
    }

    private void recover(UndoBean undoBean) {
        while (!undoBean.getResultOutStack().isEmpty()) {
            String inStr = undoBean.getResultOutStack().pop();
            this.pushIntoStack(inStr);
        }
    }
}

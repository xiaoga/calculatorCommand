package com.jay.calculator.service.command.cmd;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.model.UndoBean;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;

public class CommandUndo implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean = ApplicationContext.getUndoStack().pop();
        //System.out.println(ApplicationContext.getContextStack());
        //System.out.println(undoBean);
        this.takeOutResult(undoBean);
        this.recover(undoBean);
    }

    private void takeOutResult(UndoBean undoBean) throws ServiceException {
        while (!undoBean.getResultInStack().isEmpty()) {
            String inStr = undoBean.getResultInStack().pop();
            String rst = ApplicationContext.getContextStack().pop();
            boolean undoInfoMatch = inStr.equals(rst);
            if (!undoInfoMatch) {
                throw new ServiceException(ErrorCodeEnum.ERROR_UNDO_INFO_MISTMATCH, "undo param is:[" + inStr + "],stack param is:[" + rst + "]");
            }
        }
    }

    private void recover(UndoBean undoBean) {
        while (!undoBean.getResultOutStack().isEmpty()) {
            String inStr = undoBean.getResultOutStack().pop();
            ApplicationContext.getContextStack().push(inStr);
        }
    }
}

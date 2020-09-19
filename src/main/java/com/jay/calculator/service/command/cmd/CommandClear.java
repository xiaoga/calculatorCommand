package com.jay.calculator.service.command.cmd;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.model.UndoBean;

public class CommandClear extends AbstractCalculateCommand implements CalculateCommand{
    @Override
    public void processCommand() {
        UndoBean undoBean=new UndoBean();
        while (!ApplicationContext.getContextStack().isEmpty()){
            undoBean.getResultOutStack().push((String) ApplicationContext.getContextStack().pop());
        }
        this.setResultIn(undoBean,null);
    }
}

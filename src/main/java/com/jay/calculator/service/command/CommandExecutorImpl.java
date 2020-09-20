package com.jay.calculator.service.command;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.cmd.CalculateCommand;
import com.jay.calculator.service.command.model.UndoBean;
import com.jay.calculator.service.exception.ServiceException;

public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void execute(String command) throws ServiceException {
        OperatorCommandEnum en = OperatorCommandEnum.getByOperator(command);
        boolean existOperator = en != null;
        if (existOperator) {
            Class cls = en.getCls();
            getCommandByInput(cls).processCommand();
        } else {
            inputIntoStack(command);
        }
    }

    private CalculateCommand getCommandByInput(Class cls) {
        CalculateCommand cmd = (CalculateCommand) ApplicationContext.getContext().get(cls);
        return cmd;
    }

    private void inputIntoStack(String command) {
        ApplicationContext.getContextStack().push(command);
        UndoBean undoBean=new UndoBean();
        undoBean.getResultInStack().push(command);
        ApplicationContext.getUndoStack().push(undoBean);
    }
}

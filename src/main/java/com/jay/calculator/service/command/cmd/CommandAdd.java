package com.jay.calculator.service.command.cmd;

import com.jay.calculator.service.command.model.ParamPairBean;
import com.jay.calculator.service.command.dal.UndoBean;
import com.jay.calculator.service.exception.ServiceException;

import java.math.BigDecimal;

public class CommandAdd extends CalculateCommandBase implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        ParamPairBean paramPairBean = new ParamPairBean();
        UndoBean undoBean = new UndoBean();
        this.getParamsForCalculate(paramPairBean, undoBean);
        BigDecimal rst = paramPairBean.getFirstNumber().add(paramPairBean.getSecondNumber());
        this.setResultIn(undoBean,rst.toString());
        //System.out.println(ApplicationContext.getContextStack());
    }
}

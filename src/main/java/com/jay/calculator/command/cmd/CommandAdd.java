package com.jay.calculator.command.cmd;

import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.command.model.ParamPairBean;
import com.jay.calculator.common.exception.ManInfo;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.bean.AutoWired;
import com.jay.calculator.container.bean.Service;

import java.math.BigDecimal;

@Service()
@ManInfo(usage = "command:'+' sample: 1+2=3, you need to input '1 2 +' and out put wii be '3'")
public class CommandAdd implements CalculateCommand {

    @AutoWired(type = CalculateCommandBase.class)
    CalculateCommandBase calculateCommandBase;

    @Override
    public void processCommand() throws ServiceException {
        ParamPairBean paramPairBean = new ParamPairBean();
        UndoBean undoBean = new UndoBean();
        calculateCommandBase.getParamsForCalculate(paramPairBean, undoBean);
        BigDecimal rst = paramPairBean.getFirstNumber().add(paramPairBean.getSecondNumber());
        calculateCommandBase.setResultIn(undoBean, rst.toString());
    }
}

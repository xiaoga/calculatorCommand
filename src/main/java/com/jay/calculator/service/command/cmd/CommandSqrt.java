package com.jay.calculator.service.command.cmd;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.model.UndoBean;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;

import java.math.BigDecimal;

public class CommandSqrt extends AbstractCalculateCommand implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean=new UndoBean();
        Double bigDecimal = null;
        try {
            String number = (String) ApplicationContext.getContextStack().pop();
            undoBean.getResultOutStack().push(number);
            bigDecimal = Double.valueOf(number);
        } catch (Exception exception) {
            throw new ServiceException(ErrorCodeEnum.ERROR_PARAM_IS_NOT_NUMBER, "number format exception", exception);
        }
        BigDecimal rst = new BigDecimal(Math.sqrt(bigDecimal));
        this.setResultIn(undoBean,rst.toString());

    }
}

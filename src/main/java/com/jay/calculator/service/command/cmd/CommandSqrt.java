package com.jay.calculator.service.command.cmd;

import com.jay.calculator.service.command.dal.UndoBean;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;

import java.math.BigDecimal;

public class CommandSqrt extends CalculateCommandBase implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean=new UndoBean();
        Double doubleNumber = null;
        try {
            String number = this.getSingleParamFromStack(undoBean);
            doubleNumber = Double.valueOf(number);
        } catch (Exception exception) {
            throw new ServiceException(ErrorCodeEnum.ERROR_PARAM_IS_NOT_NUMBER, "number format exception", exception);
        }
        BigDecimal rst = new BigDecimal(Math.sqrt(doubleNumber));
        this.setResultIn(undoBean,rst.toString());

    }
}

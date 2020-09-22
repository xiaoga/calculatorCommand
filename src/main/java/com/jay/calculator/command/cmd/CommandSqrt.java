package com.jay.calculator.command.cmd;

import com.jay.calculator.command.OperatorCommandEnum;
import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.command.ManInfo;
import com.jay.calculator.container.bean.Service;

import java.math.BigDecimal;

@Service()
@ManInfo(command= OperatorCommandEnum.SQRT,usage = "'sqrt'. sample: 4 sqrt=2, you need to input '4 sqrt' and out put wii be '2'")
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

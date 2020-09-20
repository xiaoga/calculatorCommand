package com.jay.calculator.service.command.cmd;

import com.jay.calculator.service.command.model.ParamPairBean;
import com.jay.calculator.service.command.model.UndoBean;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;

import java.math.BigDecimal;

public class CommandDivide extends AbstractCalculateCommand implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        ParamPairBean paramPairBean = new ParamPairBean();
        UndoBean undoBean = new UndoBean();
        this.getParamsForCalculate(paramPairBean, undoBean);
        BigDecimal denominate=paramPairBean.getSecondNumber();
        boolean denominateIsZero= denominate.equals(new BigDecimal(0));
        if (denominateIsZero){
            throw new ServiceException(ErrorCodeEnum.ERROR_DENOMINATOR_IS_ZERO,"denominator is zero which is not allowed in divide operation");
        }
        BigDecimal rst = paramPairBean.getFirstNumber().divide(denominate);
        this.setResultIn(undoBean,rst.toString());
        //System.out.println(ApplicationContext.getContextStack());

    }
}

package com.jay.calculator.service.command.cmd;

import com.jay.calculator.ApplicationContext;
import com.jay.calculator.service.command.model.ParamPairBean;
import com.jay.calculator.service.command.model.UndoBean;
import com.jay.calculator.service.exception.ErrorCodeEnum;
import com.jay.calculator.service.exception.ServiceException;

import java.math.BigDecimal;

public abstract class AbstractCalculateCommand implements CalculateCommand {

    protected void getParamsForCalculate(ParamPairBean paramPairBean, UndoBean undoBean) throws ServiceException {
        String elememnt1 = null;
        String elememnt2 = null;
        try {
            elememnt1 = (String) ApplicationContext.getContextStack().pop();
            elememnt2 = (String) ApplicationContext.getContextStack().pop();

            undoBean.getResultOutStack().push(elememnt1);
            undoBean.getResultOutStack().push(elememnt2);
        } catch (Exception exception) {
            throw new ServiceException(ErrorCodeEnum.ERROR_INSUFFICIENT_PARAM, "insufficient numbers! ", exception);
        }
        try {
            BigDecimal secondNumber = new BigDecimal(elememnt1);
            BigDecimal firstNumber = new BigDecimal(elememnt2);
            paramPairBean.setFirstNumber(firstNumber);
            paramPairBean.setSecondNumber(secondNumber);
        } catch (Exception exception) {
            throw new ServiceException(ErrorCodeEnum.ERROR_PARAM_IS_NOT_NUMBER, "number format exception", exception);
        }
    }

    protected void setResultIn(UndoBean undoBean, String rst) {
        undoBean.getResultInStack().push(rst);
        ApplicationContext.getUndoStack().push(undoBean);
        boolean rstExist = rst != null;
        if (rstExist)
            ApplicationContext.getContextStack().push(rst.toString());
    }
}

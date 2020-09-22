package com.jay.calculator.command.cmd;

import com.jay.calculator.command.OperatorCommandEnum;
import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.command.model.ParamPairBean;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.command.ManInfo;
import com.jay.calculator.container.bean.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service()
@ManInfo(command= OperatorCommandEnum.DIVIDE,usage = "'/' sample: 8/4=3, you need to input '8 4 /' and out put will be '2'")
public class CommandDivide extends CalculateCommandBase implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        ParamPairBean paramPairBean = new ParamPairBean();
        UndoBean undoBean = new UndoBean();
        this.getParamsForCalculate(paramPairBean, undoBean);
        BigDecimal denominate = paramPairBean.getSecondNumber();
        // denominate can not be zero
        checkDenominateZero(denominate);
        // divide need to set scale and roundMode to avoid of having some issues
        BigDecimal rst = paramPairBean.getFirstNumber().divide(denominate,15, RoundingMode.DOWN);
        rst=rst.stripTrailingZeros();
        this.setResultIn(undoBean, rst.toString());
        //System.out.println(ApplicationContext.getContextStack());
    }

    private void checkDenominateZero(BigDecimal denominate) throws ServiceException {
        boolean denominateIsZero = denominate.equals(new BigDecimal(0));
        if (denominateIsZero) {
            throw new ServiceException(ErrorCodeEnum.ERROR_DENOMINATOR_IS_ZERO, "denominator is zero which is not allowed in divide operation");
        }
    }
}

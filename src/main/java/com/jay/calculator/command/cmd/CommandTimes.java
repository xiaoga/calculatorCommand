package com.jay.calculator.command.cmd;

import com.jay.calculator.command.OperatorCommandEnum;
import com.jay.calculator.command.model.ParamPairBean;
import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.common.exception.ManInfo;
import com.jay.calculator.container.bean.Service;

import java.math.BigDecimal;

@Service()
@ManInfo(usage = "command:'*'. sample: 4*4=16, you need to input '4 4 *' and out put wii be '16'")
public class CommandTimes extends CalculateCommandBase implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        ParamPairBean paramPairBean = new ParamPairBean();
        UndoBean undoBean = new UndoBean();
        this.getParamsForCalculate(paramPairBean, undoBean);
        BigDecimal rst = paramPairBean.getFirstNumber().multiply(paramPairBean.getSecondNumber());
        this.setResultIn(undoBean,rst.toString());
    }
}

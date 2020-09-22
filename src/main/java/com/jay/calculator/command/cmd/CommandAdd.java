package com.jay.calculator.command.cmd;

import com.jay.calculator.command.OperatorCommandEnum;
import com.jay.calculator.command.model.ParamPairBean;
import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.command.ManInfo;
import com.jay.calculator.container.bean.Service;

import java.math.BigDecimal;

@Service()
@ManInfo(command= OperatorCommandEnum.ADD,usage = "'+' sample: 1+2=3, you need to input '1 2 +' and out put wii be '3'")
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

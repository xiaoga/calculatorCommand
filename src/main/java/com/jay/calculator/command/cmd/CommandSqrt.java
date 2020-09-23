package com.jay.calculator.command.cmd;

import com.jay.calculator.command.dal.UndoBean;
import com.jay.calculator.common.exception.ManInfo;
import com.jay.calculator.common.exception.ServiceException;
import com.jay.calculator.container.bean.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service()
@ManInfo(usage = "command:'sqrt'. sample: 4 sqrt=2, you need to input '4 sqrt' and out put wii be '2'")
public class CommandSqrt extends CalculateCommandBase implements CalculateCommand {
    @Override
    public void processCommand() throws ServiceException {
        UndoBean undoBean = new UndoBean();
        String number = this.getSingleParamFromStack(undoBean);
        Double doubleNumber = Double.valueOf(number);
        BigDecimal rst = new BigDecimal(Math.sqrt(doubleNumber)).setScale(15, RoundingMode.DOWN);
        this.setResultIn(undoBean, rst.toString());
    }
}

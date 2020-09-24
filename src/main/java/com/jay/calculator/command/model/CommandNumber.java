package com.jay.calculator.command.model;

import com.jay.calculator.common.exception.CommandConstant;
import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;

import java.math.BigDecimal;
import java.math.RoundingMode;

//[requirement] to meet the requirement of 15 precision
public class CommandNumber {

    public CommandNumber(String strNumber) throws ServiceException {
        this.strNumber = strNumber;
        this.bigDecimal10 = this.getBigDecimalByScale(CommandConstant.PRECISION_10);
        this.bigDecimal15 = this.getBigDecimalByScale(CommandConstant.PRECISION_15);
    }

    private String strNumber;

    private BigDecimal bigDecimal10;

    private BigDecimal bigDecimal15;


    private BigDecimal getBigDecimalByScale(int scale) throws ServiceException {
        // check whether it is decimal, if no, throw exception
        try {
            new BigDecimal(this.strNumber);
        } catch (Exception exception) {
            throw new ServiceException(ErrorCodeEnum.ERROR_PARAM_IS_NOT_NUMBER, "current input is not number! input is:[" + this.strNumber + "]");
        }
        BigDecimal bigDecimal = new BigDecimal(this.strNumber);
        boolean existNumberRightSidePoint = strNumber.contains(CommandConstant.DOT);
        if (existNumberRightSidePoint) {
            String rightNumber = strNumber.substring(strNumber.indexOf(CommandConstant.DOT) + 1);
            boolean rightNumberMoreThanScale = rightNumber.length() > scale;
            if (rightNumberMoreThanScale) {
                bigDecimal=bigDecimal.setScale(scale, RoundingMode.DOWN);
            }
        }
        return bigDecimal;
    }

    public BigDecimal getBigDecimal10() {
        return bigDecimal10;
    }

    public BigDecimal getBigDecimal15() {
        return bigDecimal15;
    }

    @Override
    public String toString() {
        return "Number{" +
                "strNumber='" + strNumber + '\'' +
                ", bigDecimal10=" + bigDecimal10 +
                ", bigDecimal15=" + bigDecimal15 +
                '}';
    }
}

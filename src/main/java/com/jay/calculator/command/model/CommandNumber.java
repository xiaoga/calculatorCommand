package com.jay.calculator.command.model;

import com.jay.calculator.common.exception.ErrorCodeEnum;
import com.jay.calculator.common.exception.ServiceException;

import java.math.BigDecimal;
//[requirement] to meet the requirement of 15 precision
public class CommandNumber {

    public CommandNumber(String strNumber) throws ServiceException {
        this.strNumber = strNumber;
        this.bigDecimal10 = this.getBigDecimalByScale(10);
        this.bigDecimal15 = this.getBigDecimalByScale(15);
    }

    private String strNumber;

    private BigDecimal bigDecimal10;

    private BigDecimal bigDecimal15;


    private BigDecimal getBigDecimalByScale(int scale) throws ServiceException {
        try {
            BigDecimal bigDecimal = new BigDecimal(this.strNumber);
        } catch (Exception exception) {
            throw new ServiceException(ErrorCodeEnum.ERROR_PARAM_IS_NOT_NUMBER, "current input is not number! input is:[" + this.strNumber + "]");
        }
        BigDecimal bigDecimal = new BigDecimal(this.strNumber);
        boolean existNumberRightSidePoint = strNumber.contains(".");
        if (existNumberRightSidePoint) {
            String rightNumber = strNumber.substring(strNumber.indexOf(".") + 1);
            boolean rightNumberMoreThanScale = rightNumber.length() > scale;
            if (rightNumberMoreThanScale) {
                String resultString = strNumber.substring(0,strNumber.indexOf(".") + 1+scale);
                bigDecimal=new BigDecimal(resultString);
            }
        }
        return bigDecimal;
    }

    public String getStrNumber() {
        return strNumber;
    }

    public void setStrNumber(String strNumber) {
        this.strNumber = strNumber;
    }

    public BigDecimal getBigDecimal10() {
        return bigDecimal10;
    }

    public void setBigDecimal10(BigDecimal bigDecimal10) {
        this.bigDecimal10 = bigDecimal10;
    }

    public BigDecimal getBigDecimal15() {
        return bigDecimal15;
    }

    public void setBigDecimal15(BigDecimal bigDecimal15) {
        this.bigDecimal15 = bigDecimal15;
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

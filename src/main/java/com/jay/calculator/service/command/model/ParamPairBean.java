package com.jay.calculator.service.command.model;

import java.math.BigDecimal;

public class ParamPairBean {
    BigDecimal firstNumber;
    BigDecimal SecondNumber;

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return SecondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        SecondNumber = secondNumber;
    }
}

package com.jay.test.internal;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBigDecimal {

    @Test
    public void testDivide(){
        BigDecimal a=new BigDecimal(4);
        BigDecimal b=new BigDecimal(3);
        System.out.println(a.divide(b,15, RoundingMode.DOWN));
    }
}

package com.net.security.utils;

import java.math.BigDecimal;

public class FloatSolve {
    public static float transfloat(float num){
        BigDecimal b=new BigDecimal(num);
        return b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    }
}

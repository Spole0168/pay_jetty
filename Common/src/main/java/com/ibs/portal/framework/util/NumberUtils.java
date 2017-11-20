package com.ibs.portal.framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils extends org.apache.commons.lang.math.NumberUtils {

    public static Double getDouble(BigDecimal v) {
        Double rtn = null;

        if (v != null)
            rtn = createDouble(v.toString());

        return rtn;
    }

    public static BigDecimal getBigDecimal(Double v) {
        BigDecimal rtn = null;

        if (v != null)
            rtn = createBigDecimal(v.toString());

        return rtn;
    }

    public static String addOne(BigDecimal length, String currentValue) {
        String pattern = String.format("%0" + length + "d", 0);
        Integer newValue = Integer.parseInt(currentValue);
        newValue++;
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(newValue);
    }

}

package com.ibs.portal.framework.util;

import java.util.Date;

import org.junit.Test;

public class DataUtilsTest {
    
    @Test
    public void testConvert2String(){
        System.out.println(DateUtils.convert(new Date(), "yyyyMMdd"));
    }

}

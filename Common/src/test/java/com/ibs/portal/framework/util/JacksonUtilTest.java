package com.ibs.portal.framework.util;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

public class JacksonUtilTest {
    
    @Test
    public void testReadValue() throws Exception {
       A a = (A)JacksonUtil.readValue("{\"a1\": \"a1\", \"a2\": 2}", A.class);
       assertEquals("a1", a.getA1());
       assertEquals(2, a.getA2());
       
       a = (A)JacksonUtil.readValue("{ \"a2\": 2}", A.class);
       assertEquals(null, a.getA1());
       
       Map map  = (Map)JacksonUtil.readValue("{ \"a2\": 2}", Map.class);
       assertEquals(1, map.size());
    }

}

class A {
    private String a1;
    private int a2;
    
    public String getA1() {
        return a1;
    }
    public void setA1(String a1) {
        this.a1 = a1;
    }
    public int getA2() {
        return a2;
    }
    public void setA2(int a2) {
        this.a2 = a2;
    }
    
}
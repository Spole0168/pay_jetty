package com.ibs.core.test;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public abstract class AbstractTestBean extends AbstractDependencyInjectionSpringContextTests {

    @Override
    protected String[] getConfigLocations() {
        String[] xmls = new String[] { "classpath*:com/ibs/common/module/frameworkimpl/commonconfig/META-INF/config/beans.xml", "classpath*:*Context.xml" };
        return (String[]) ArrayUtils.addAll(xmls, getModuleConfigs());
    }
    
    abstract protected String[] getModuleConfigs();

    protected void init() throws Exception {

    }

}

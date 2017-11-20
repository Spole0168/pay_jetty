package com.ibs.core.module.basefunc.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.core.test.AbstractTestBeanWithTransactionSupport;

public class BasefuncTestBase extends AbstractTestBeanWithTransactionSupport{

    @Override
    protected String[] getModuleConfigs() {
        return new String[] { "classpath*:com/ibs/core/module/basefunc/META-INF/config/beans.xml",
        		"classpath*:com/ibs/core/module/mdmbasedata/META-INF/config/beans.xml",
                "classpath*:basicdata/deployContext.xml"};
    }
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}

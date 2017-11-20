package com.ibs.core.module.basicdata.test.dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibs.core.module.basefunc.service.IAuditService;

public class BasicDataServiceConsumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "classpath*:basicdata-services-consumer.xml" });
        context.start();

        IAuditService auditService = (IAuditService) context.getBean("auditService");
        auditService.saveAudit(null);
        System.out.println("save audit is done");
    }

}

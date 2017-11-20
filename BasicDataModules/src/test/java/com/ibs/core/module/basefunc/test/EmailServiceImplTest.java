package com.ibs.core.module.basefunc.test;

import org.junit.Test;

import com.ibs.core.module.basefunc.service.IEmailService;

public class EmailServiceImplTest extends BasefuncTestBase {
    private IEmailService emailService;
    /**
     * 邮件发送本地测试和部署环境不同，本地使用163测试需要使用SSL安全协议
     */
    @Test
    public void testSendEmail(){
        logger.info("into testSendEmail method...");
        
        emailService.sendEmail("355542483@qq.com","邮件主题", "邮件内容",  null);
    }

    public IEmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(IEmailService emailService) {
        this.emailService = emailService;
    }

}

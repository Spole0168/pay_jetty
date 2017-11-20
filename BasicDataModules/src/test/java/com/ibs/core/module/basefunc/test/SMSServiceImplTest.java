package com.ibs.core.module.basefunc.test;

import org.junit.Test;

import com.ibs.core.module.basefunc.service.ISMSService;

public class SMSServiceImplTest extends BasefuncTestBase {

    private ISMSService SMSService;
    @Test
    public void testGetDefaultSMS(){
        logger.info("into testGetDefaultSMS method...");
        String sms = SMSService.getDefaultSMS();
        boolean res = sms.matches("[\\d]{4}");
        assertTrue(res);
    }
    
    @Test
    public void testGetSMSByLength(){
        logger.info("into testGetSMSByLength method...");
        String sms = SMSService.getSMSByLength(6);
        boolean res = sms.matches("[\\d]{"+6+"}");
        assertTrue(res);
    }
    
    @Test
    public void testSendSMS1(){
        String phone = "18221569925";
        String authCode = SMSService.sendSMS(phone);
        boolean res = authCode.matches("[\\d]{4}");
        assertTrue(res);
    }
    
    @Test
    public void testSendSMS2(){
        String phone = "18221569925";
        String content = "this is a SMS test"; 
        String resContent = SMSService.sendSMS(phone,content);
        assertEquals(content, resContent);
        
    }
    public ISMSService getSMSService() {
        return SMSService;
    }

    public void setSMSService(ISMSService sMSService) {
        SMSService = sMSService;
    }

}

package com.ibs.core.module.basefunc.service.impl;

import java.util.Date;
import java.util.Random;

import com.ibs.core.module.basefunc.service.ISMSService;
import com.ibs.portal.framework.util.DateUtils;

public class SMSServiceImpl implements ISMSService {

    @Override
    public String getDefaultSMS() {
        return getSMSByLength(4);
    }
    
    @Override
    public String getSMSByLength(int length) {
        if (length > 0) {
            StringBuffer buf = new StringBuffer();
            String str = "0123456789";
            Random random = new Random(new Long(DateUtils.convert(new Date(), "yyyyMMddHHmmss")));
            for (int i = 0; i < length; i++) {
                int num = random.nextInt(10);
                buf.append(str.charAt(num));
            }
            return buf.toString();
        }
        return null;
    }

    @Override
    public String sendSMS(String phoneNum) {
        //生成四位随机验证码
        String authCode = getDefaultSMS();
        String content = "..." + authCode + "...";
        //发送短信
        //to do 
        return authCode;
    }

    @Override
    public String sendSMS(String phoneNum, String content) {
        //发送短信
        //to do
        return content;
    }

}

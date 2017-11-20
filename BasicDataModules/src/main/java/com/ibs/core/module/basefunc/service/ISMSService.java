package com.ibs.core.module.basefunc.service;

public interface ISMSService {
    /**
     * 生成短信验证码，默认4位数字
     * @return
     */
    String getDefaultSMS();
    
    /**
     * 生成短信验证码
     * @param length 验证码长度
     * @return
     */
    String getSMSByLength(int length);
    /**
     * 发送一个随机四位数短信验证码并返回
     * @param phoneNum  手机号码
     * @return
     */
    String sendSMS(String phoneNum);
    /**
     * 发送一个确定内容的短信
     * @param phoneNum
     * @param content
     * @return content
     */
    String sendSMS(String phoneNum,String content);
}

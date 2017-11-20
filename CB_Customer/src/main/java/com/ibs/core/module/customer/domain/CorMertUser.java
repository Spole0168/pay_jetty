/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 会员登录信息实体 Creator : Spole Date : 2016年12月14日
 */
public class CorMertUser extends BaseEntity {

    private String id; // ID
    private String custCode; // CUST_CODE
    private String merchantCode; // MERCHANT_CODE
    private String userCode; // USER_CODE
    private String userName; // USER_NAME
    private String password; // PASSWORD
    private String smsCertCode; // SMS_CERT_CODE
    private String phoneNum; // PHONE_NUM
    private String email; // EMAIL
    private String lastLoginTime; // LAST_LOGIN_TIME
    private String lastLoginIP; // LAST_LOGIN_IP
    private String defaultLocale; // DEFAULT_LOCALE
    private Date pwdUpdateTime; // PWD_UPDATE_TIME
    private String status; // STATUS
    private String creator; // CREATOR
    private Date createTime; // CREATE_TIME
    private String updator; // UPDATOR
    private Date updateTime; // UPDATE_TIME
    private String isValid; // IS_VALID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCertCode() {
        return smsCertCode;
    }

    public void setSmsCertCode(String smsCertCode) {
        this.smsCertCode = smsCertCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public Date getPwdUpdateTime() {
        return pwdUpdateTime;
    }

    public void setPwdUpdateTime(Date pwdUpdateTime) {
        this.pwdUpdateTime = pwdUpdateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

}

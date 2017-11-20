/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.domain;

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_CARDBIND_MERT_CUST
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorCardbindMertCust extends BaseEntity {

    private String id; // ID
    private String custCode; // CUST_CODE
    private String merchantCode; // MERCHANT_CODE
    private String mertCustCode; // MERT_CUST_CODE
    private String bankCardNum; // BANK_CARD_NUM
    private String status; // STATUS
    private String creator; // CREATOR
    private Date createTime; // CREATE_TIME
    private String updator; // UPDATOR
    private Date updateTime; // UPDATE_TIME
    private String isValid; // IS_VALID
    private String token; // TOKEN

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustCode() {
        return custCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getMertCustCode() {
        return mertCustCode;
    }

    public void setMertCustCode(String mertCustCode) {
        this.mertCustCode = mertCustCode;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
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

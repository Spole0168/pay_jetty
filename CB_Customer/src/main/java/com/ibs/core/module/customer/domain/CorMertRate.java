/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 商户费率实体对象 Creator : Spole Date : 2016年12月14日
 */
public class CorMertRate extends BaseEntity {

    private static final long serialVersionUID = 1727074106738704207L;

    private String id; // ID
    private String mertRateCode; // MERT_RATE_CODE
    private String mertRateName; // MERT_RATE_NAME
    private String custCode; // CUST_CODE
    private String merchantCode; // MERCHANT_CODE
    private String productCode; // PRODUCT_CODE
    private String cardType; // CARD_TYPECorMertRate
    private String cardProperty; // CARD_PROPERTY
    private String settType; // SETT_TYPE
    private String currency; // CURRENCY
    private String serviceFeeType; // SERVICE_FEE_TYPE
    private BigDecimal fixedFeeValue; // FIXED_FEE_VALUE
    private BigDecimal rateFeeValue; // RATE_FEE_VALUE
    private BigDecimal lowFeeValue; // LOW_FEE_VALUE
    private BigDecimal hightFeeValue; // HIGHT_FEE_VALUE
    private Date effectDate; // EFFECT_DATE
    private Date expireDate; // EXPIRE_DATE
    private String creator; // CREATOR
    private Date createTime; // CREATE_TIME
    private String updator; // UPDATOR
    private Date updateTime; // UPDATE_TIME
    private String isValid; // IS_VALID
    private String remark; // REMARK

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMertRateCode() {
        return mertRateCode;
    }

    public void setMertRateCode(String mertRateCode) {
        this.mertRateCode = mertRateCode;
    }

    public String getMertRateName() {
        return mertRateName;
    }

    public void setMertRateName(String mertRateName) {
        this.mertRateName = mertRateName;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardProperty() {
        return cardProperty;
    }

    public void setCardProperty(String cardProperty) {
        this.cardProperty = cardProperty;
    }

    public String getSettType() {
        return settType;
    }

    public void setSettType(String settType) {
        this.settType = settType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getServiceFeeType() {
        return serviceFeeType;
    }

    public void setServiceFeeType(String serviceFeeType) {
        this.serviceFeeType = serviceFeeType;
    }

    public BigDecimal getFixedFeeValue() {
        return fixedFeeValue;
    }

    public void setFixedFeeValue(BigDecimal fixedFeeValue) {
        this.fixedFeeValue = fixedFeeValue;
    }

    public BigDecimal getRateFeeValue() {
        return rateFeeValue;
    }

    public void setRateFeeValue(BigDecimal rateFeeValue) {
        this.rateFeeValue = rateFeeValue;
    }

    public BigDecimal getLowFeeValue() {
        return lowFeeValue;
    }

    public void setLowFeeValue(BigDecimal lowFeeValue) {
        this.lowFeeValue = lowFeeValue;
    }

    public BigDecimal getHightFeeValue() {
        return hightFeeValue;
    }

    public void setHightFeeValue(BigDecimal hightFeeValue) {
        this.hightFeeValue = hightFeeValue;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}

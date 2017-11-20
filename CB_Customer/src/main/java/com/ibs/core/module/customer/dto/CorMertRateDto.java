package com.ibs.core.module.customer.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class CorMertRateDto extends BaseEntity {

    private static final long serialVersionUID = -5086422256616363993L;

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

    private String merchantName;
    private String productName;

    // 查询条件
    private String validDate;

    // opreaType = 0;//opreaType : 0 -新增，1-查看，2-审核，3-修改
    private String operaType; // 操作类型

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }

}

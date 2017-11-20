package com.ibs.core.module.customer.domain;

import java.math.BigDecimal;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class CorMertRateResult  extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -8058618795473529362L;
    private String serviceFeeType;
    private BigDecimal fixedFeeValue;
    private BigDecimal rateFeeValue;
    private BigDecimal lowFeeValue;
    private BigDecimal hightFeeValue;
    private String currency; //手续费币种
    private BigDecimal amount;//手续费金额
    
    

    public CorMertRateResult() {
        super();
    }

    public CorMertRateResult(String serviceFeeType, BigDecimal fixedFeeValue, BigDecimal rateFeeValue,
            BigDecimal lowFeeValue, BigDecimal hightFeeValue,String currency) {
        super();
        this.serviceFeeType = serviceFeeType;
        this.fixedFeeValue = fixedFeeValue;
        this.rateFeeValue = rateFeeValue;
        this.lowFeeValue = lowFeeValue;
        this.hightFeeValue = hightFeeValue;
        this.currency = currency;
       
    }

    public CorMertRateResult(CorMertRate corMertRate) {
        this.serviceFeeType = corMertRate.getServiceFeeType();
        this.fixedFeeValue = corMertRate.getFixedFeeValue();
        this.rateFeeValue = corMertRate.getRateFeeValue();
        this.lowFeeValue = corMertRate.getLowFeeValue();
        this.hightFeeValue = corMertRate.getHightFeeValue();
        this.currency = corMertRate.getCurrency();
       
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CorMertRateResult [serviceFeeType=" + serviceFeeType + ", fixedFeeValue=" + fixedFeeValue
                + ", rateFeeValue=" + rateFeeValue + ", lowFeeValue=" + lowFeeValue + ", hightFeeValue=" + hightFeeValue
                + ", currency=" + currency + ", amount=" + amount + "]";
    }

    
}

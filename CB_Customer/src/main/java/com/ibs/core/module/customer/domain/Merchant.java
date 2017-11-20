package com.ibs.core.module.customer.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class Merchant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;
    private String custCode;
    private String merchantCode;
    private String merchantName;
    private Integer settPeriod;
    private Date settStartDate;
    private Date nextSettDate;
    private String mccCode;
    private String industry;
    private String operateScope;
    private String riskLevel;
    private BigDecimal initialDeposit;
    private String website;
    private String settlementMode;
    private Long total3mCount;
    private BigDecimal total3mAmount;
    private String tecResponse;
    private String tecResponsePhone;
    private String tecResponseEmail;
    private String businessResponse;
    private String businessResponsePhone;
    private String businessResponseEmail;
    private String contractCode;
    private Date activeDate;
    private Date inactiveDate;
    private String status;
    private String isCheckin;
    private String isCheckout;
    private String creator;
    private Date createTime;
    private String updator;
    private Date updateTime;
    private String isValid;
    private String remark;

    private String voutherUploadPath;// 凭证链接

    private String modifyContent;

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

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getSettPeriod() {
        return settPeriod;
    }

    public void setSettPeriod(Integer settPeriod) {
        this.settPeriod = settPeriod;
    }

    public Date getSettStartDate() {
        return settStartDate;
    }

    public void setSettStartDate(Date settStartDate) {
        this.settStartDate = settStartDate;
    }

    public Date getNextSettDate() {
        return nextSettDate;
    }

    public void setNextSettDate(Date nextSettDate) {
        this.nextSettDate = nextSettDate;
    }

    public String getMccCode() {
        return mccCode;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getOperateScope() {
        return operateScope;
    }

    public void setOperateScope(String operateScope) {
        this.operateScope = operateScope;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public BigDecimal getInitialDeposit() {
        return initialDeposit;
    }

    public void setInitialDeposit(BigDecimal initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSettlementMode() {
        return settlementMode;
    }

    public void setSettlementMode(String settlementMode) {
        this.settlementMode = settlementMode;
    }

    public Long getTotal3mCount() {
        return total3mCount;
    }

    public void setTotal3mCount(Long total3mCount) {
        this.total3mCount = total3mCount;
    }

    public BigDecimal getTotal3mAmount() {
        return total3mAmount;
    }

    public void setTotal3mAmount(BigDecimal total3mAmount) {
        this.total3mAmount = total3mAmount;
    }

    public String getTecResponse() {
        return tecResponse;
    }

    public void setTecResponse(String tecResponse) {
        this.tecResponse = tecResponse;
    }

    public String getTecResponsePhone() {
        return tecResponsePhone;
    }

    public void setTecResponsePhone(String tecResponsePhone) {
        this.tecResponsePhone = tecResponsePhone;
    }

    public String getTecResponseEmail() {
        return tecResponseEmail;
    }

    public void setTecResponseEmail(String tecResponseEmail) {
        this.tecResponseEmail = tecResponseEmail;
    }

    public String getBusinessResponse() {
        return businessResponse;
    }

    public void setBusinessResponse(String businessResponse) {
        this.businessResponse = businessResponse;
    }

    public String getBusinessResponsePhone() {
        return businessResponsePhone;
    }

    public void setBusinessResponsePhone(String businessResponsePhone) {
        this.businessResponsePhone = businessResponsePhone;
    }

    public String getBusinessResponseEmail() {
        return businessResponseEmail;
    }

    public void setBusinessResponseEmail(String businessResponseEmail) {
        this.businessResponseEmail = businessResponseEmail;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(String isCheckin) {
        this.isCheckin = isCheckin;
    }

    public String getIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(String isCheckout) {
        this.isCheckout = isCheckout;
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

    public String getVoutherUploadPath() {
        return voutherUploadPath;
    }

    public void setVoutherUploadPath(String voutherUploadPath) {
        this.voutherUploadPath = voutherUploadPath;
    }

    public String getModifyContent() {
        return modifyContent;
    }

    public void setModifyContent(String modifyContent) {
        this.modifyContent = modifyContent;
    }

}

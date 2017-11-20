package com.ibs.core.module.customer.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class MerchantDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;
    private String custCode;// 会员编号
    private String merchantCode;// 商户号
    private String merchantName;// 商户名称
    private Integer settPeriod;// 结算周期
    private Date settStartDate;// 结算起始日
    private Date nextSettDate;// 下一结算日
    private String mccCode;// 行业类别码
    private String industry;// 行业类型
    private String operateScope;
    private String riskLevel;// 商户风险等级
    private BigDecimal initialDeposit;// 保证金初始值
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
    private String isCheckin;// 是否止入
    private String isCheckout;// 是否止出
    private String creator;// 创建人
    private Date createTime;// 创建时间
    private String updator;
    private Date updateTime;
    private String isValid;
    private String remark;

    private String custName;// 会员商户
    private String reviewPerson; // 审核人
    private Date reviewTime; // 审核时间
    private String reviewFailureReasons; // 失败原因
    private String amount;// 付款手续费
    private String rate;// 订单支付手续费

    private String startDate;
    private String endDate;

    private String voutherUploadPath;// 凭证链接
    private String operaType;

    private String modifyContent;

    private String mertStatus;// 商户状态
    private String isCheck;// 止入止出查询条件
    private String accountCode;
    private String accId;// 账户id

    private String checkSelect;
    private String checkType;

    private String auId;

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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getReviewPerson() {
        return reviewPerson;
    }

    public void setReviewPerson(String reviewPerson) {
        this.reviewPerson = reviewPerson;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getReviewFailureReasons() {
        return reviewFailureReasons;
    }

    public void setReviewFailureReasons(String reviewFailureReasons) {
        this.reviewFailureReasons = reviewFailureReasons;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
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

    public String getMertStatus() {
        return mertStatus;
    }

    public void setMertStatus(String mertStatus) {
        this.mertStatus = mertStatus;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public String getCheckSelect() {
        return checkSelect;
    }

    public void setCheckSelect(String checkSelect) {
        this.checkSelect = checkSelect;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getAuId() {
        return auId;
    }

    public void setAuId(String auId) {
        this.auId = auId;
    }

    @Override
    public String toString() {
        return "MerchantDto [id=" + id + ", custCode=" + custCode + ", merchantCode=" + merchantCode + ", merchantName="
                + merchantName + ", settPeriod=" + settPeriod + ",settStartDate=" + settStartDate + ",nextSettDate="
                + nextSettDate + ",mccCode=" + mccCode + ",industry=" + industry + ",operateScope=" + operateScope
                + ",riskLevel=" + riskLevel + ",initialDeposit=" + initialDeposit + ",website=" + website
                + ",settlementMode=" + settlementMode + ",total3mCount=" + total3mCount + ",total3mAmount="
                + total3mAmount + ",tecResponse=" + tecResponse + ",tecResponsePhone=" + tecResponsePhone
                + ",tecResponseEmail=" + tecResponseEmail + ",businessResponse=" + businessResponse
                + ",businessResponsePhone=" + businessResponsePhone + ",businessResponseEmail=" + businessResponseEmail
                + ",contractCode=" + contractCode + ",activeDate=" + activeDate + ",inactiveDate=" + inactiveDate
                + ",status=" + status + ",isCheckin=" + isCheckin + ",isCheckout=" + isCheckout + ",creator=" + creator
                + ",createTime=" + createTime + ",updator=" + updator + ",updateTime=" + updateTime + ",isValid="
                + isValid + ",remark=" + remark + ",localName=" + custName + ",reviewPerson=" + reviewPerson
                + ",localName=" + custName + ",reviewPerson=" + reviewPerson + ",reviewTime=" + reviewTime + "]";
    }

}

/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 客户会员信息实体类 Creator : Spole Date : 2016年12月14日
 */
public class CorCustCompany extends BaseEntity {

    private static final long serialVersionUID = 6194951026221903305L;

    private String id; // ID
    private String custCode; // CUST_CODE
    private String socialCreditId; // SOCIAL_CREDIT_ID
    private String businessLicense; // BUSINESS_LICENSE
    private Date businessLicenseExpiry; // BUSINESS_LICENSE_EXPIRY
    private String taxId; // TAX_ID
    private String companyCode; // COMPANY_CODE
    private String corporateName; // CORPORATE_NAME
    private String corporateCountryCode; // CORPORATE_COUNTRY_CODE
    private String corporateCertType; // CORPORATE_CERT_TYPE
    private String corporateCertNum; // CORPORATE_CERT_NUM
    private String corporateCertCopy; // CORPORATE_CERT_COPY
    private Date corporateCertExpireDate; // CORPORATE_CERT_EXPIRE_DATE
    private String corporatePhonenum; // CORPORATE_PHONENUM
    private String contact; // CONTACT
    private String contactPhone; // CONTACT_PHONE
    private String contactEmail; // CONTACT_EMAIL
    private String country; // COUNTRY
    private Date regTime; // REG_TIME
    private String unitType; // UNIT_TYPE
    private String unitProperty; // UNIT_PROPERTY
    private String businessScope; // BUSINESS_SCOPE
    private String industry; // INDUSTRY
    private String operateScope; // OPERATE_SCOPE
    private String companyRegAddr; // COMPANY_REG_ADDR
    private BigDecimal registeredFund; // REGISTERED_FUND
    private String registeredCurrency; // REGISTERED_CURRENCY
    private String financeContact; // FINANCE_CONTACT
    private String financeContactPhone; // FINANCE_CONTACT_PHONE
    private String financeContactFax; // FINANCE_CONTACT_FAX
    private String email; // EMAIL
    private String companyFax; // COMPANY_FAX
    private String companyWebsite; // COMPANY_WEBSITE
    private String riskLevel; // RISK_LEVEL
    private String voucher; // VOUCHER
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

    public String getSocialCreditId() {
        return socialCreditId;
    }

    public void setSocialCreditId(String socialCreditId) {
        this.socialCreditId = socialCreditId;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public Date getBusinessLicenseExpiry() {
        return businessLicenseExpiry;
    }

    public void setBusinessLicenseExpiry(Date businessLicenseExpiry) {
        this.businessLicenseExpiry = businessLicenseExpiry;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getCorporateCountryCode() {
        return corporateCountryCode;
    }

    public void setCorporateCountryCode(String corporateCountryCode) {
        this.corporateCountryCode = corporateCountryCode;
    }

    public String getCorporateCertType() {
        return corporateCertType;
    }

    public void setCorporateCertType(String corporateCertType) {
        this.corporateCertType = corporateCertType;
    }

    public String getCorporateCertNum() {
        return corporateCertNum;
    }

    public void setCorporateCertNum(String corporateCertNum) {
        this.corporateCertNum = corporateCertNum;
    }

    public String getCorporateCertCopy() {
        return corporateCertCopy;
    }

    public void setCorporateCertCopy(String corporateCertCopy) {
        this.corporateCertCopy = corporateCertCopy;
    }

    public Date getCorporateCertExpireDate() {
        return corporateCertExpireDate;
    }

    public void setCorporateCertExpireDate(Date corporateCertExpireDate) {
        this.corporateCertExpireDate = corporateCertExpireDate;
    }

    public String getCorporatePhonenum() {
        return corporatePhonenum;
    }

    public void setCorporatePhonenum(String corporatePhonenum) {
        this.corporatePhonenum = corporatePhonenum;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitProperty() {
        return unitProperty;
    }

    public void setUnitProperty(String unitProperty) {
        this.unitProperty = unitProperty;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
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

    public String getCompanyRegAddr() {
        return companyRegAddr;
    }

    public void setCompanyRegAddr(String companyRegAddr) {
        this.companyRegAddr = companyRegAddr;
    }

    public BigDecimal getRegisteredFund() {
        return registeredFund;
    }

    public void setRegisteredFund(BigDecimal registeredFund) {
        this.registeredFund = registeredFund;
    }

    public String getRegisteredCurrency() {
        return registeredCurrency;
    }

    public void setRegisteredCurrency(String registeredCurrency) {
        this.registeredCurrency = registeredCurrency;
    }

    public String getFinanceContact() {
        return financeContact;
    }

    public void setFinanceContact(String financeContact) {
        this.financeContact = financeContact;
    }

    public String getFinanceContactPhone() {
        return financeContactPhone;
    }

    public void setFinanceContactPhone(String financeContactPhone) {
        this.financeContactPhone = financeContactPhone;
    }

    public String getFinanceContactFax() {
        return financeContactFax;
    }

    public void setFinanceContactFax(String financeContactFax) {
        this.financeContactFax = financeContactFax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
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

    @Override
    public String toString() {
        return "CorCustCompany [id=" + id + ", custCode=" + custCode + ", socialCreditId=" + socialCreditId
                + ", businessLicense=" + businessLicense + ", businessLicenseExpiry=" + businessLicenseExpiry
                + ", taxId=" + taxId + ", companyCode=" + companyCode + ", corporateName=" + corporateName
                + ", corporateCountryCode=" + corporateCountryCode + ", corporateCertType=" + corporateCertType
                + ", corporateCertNum=" + corporateCertNum + ", corporateCertCopy=" + corporateCertCopy
                + ", corporateCertExpireDate=" + corporateCertExpireDate + ", corporatePhonenum=" + corporatePhonenum
                + ", contact=" + contact + ", contactPhone=" + contactPhone + ", contactEmail=" + contactEmail
                + ", country=" + country + ", regTime=" + regTime + ", unitType=" + unitType + ", unitProperty="
                + unitProperty + ", businessScope=" + businessScope + ", industry=" + industry + ", operateScope="
                + operateScope + ", companyRegAddr=" + companyRegAddr + ", registeredFund=" + registeredFund
                + ", registeredCurrency=" + registeredCurrency + ", financeContact=" + financeContact
                + ", financeContactPhone=" + financeContactPhone + ", financeContactFax=" + financeContactFax
                + ", email=" + email + ", companyFax=" + companyFax + ", companyWebsite=" + companyWebsite
                + ", riskLevel=" + riskLevel + ", voucher=" + voucher + ", creator=" + creator + ", createTime="
                + createTime + ", updator=" + updator + ", updateTime=" + updateTime + ", isValid=" + isValid + "]";
    }

}

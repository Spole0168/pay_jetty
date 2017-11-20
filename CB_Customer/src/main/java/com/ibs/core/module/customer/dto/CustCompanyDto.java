package com.ibs.core.module.customer.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class CustCompanyDto extends BaseEntity {

    private static final long serialVersionUID = 7067812546094562159L;

    // cust 客户信息
    private String id; // ID
    // private String custCode; // CUST_CODE
    private String custType; // CUST_TYPE
    private String country; // COUNTRY
    private String provience;
    private String city;
    private String region;
    // private String localName; // LOCAL_NAME
    private String realNameLeve; // REAL_NAME_LEVE
    private String firstName; // FIRST_NAME
    private String lastName; // LAST_NAME
    private String certType; // CERT_TYPE
    private String certNum; // CERT_NUM
    private Date certExpireDate; // CERT_EXPIRE_DATE
    private String certCopy; // CERT_COPY
    private String custStatus; // STATUS
    private Date regTime; // REG_TIME
    private String dataSource; // DATA_SOURCE
    private String creator; // CREATOR
    private Date createTime; // CREATE_TIME
    private String updator; // UPDATOR
    private Date updateTime; // UPDATE_TIME
    private String remark; // REMARK
    private String isValid; // IS_VALID

    // 公司客户信息表 custCompany
    // private String id; // ID
    // private String custCode; // CUST_CODE
    // private String socialCreditId; // SOCIAL_CREDIT_ID
    // private String businessLicense; // BUSINESS_LICENSE
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
    private String postcode; //
    // private String country; // COUNTRY
    // private Date regTime; // REG_TIME
    // private String unitType; // UNIT_TYPE
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

    // 审核信息
    private String auditType; // AUDIT_TYPE
    private String sourceCode; // SOURCE_CODE
    // private String status; // STATUS
    private String auditReason; // AUDIT_REASON
    private String auditPerson; // AUDIT_REASON
    private Date auditTime; // AUDIT_REASON

    private String cid;// cust--id
    private String ccid;// cust_company --id
    private String caid;// Audit --id
    private String cmuid; // 生成登录用户的id
    // 查询条件
    private String custCode;
    private String localName;
    private String unitType;
    private String socialCreditId;
    private String businessLicense;
    private String startCreateTime;
    private String endCreateTime;
    private String status; // 审核状态
    // opreaType = 0;//opreaType : 0 -新增，1-查看，2-审核，3-修改
    private String operaType; // 操作类型

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public String getCaid() {
        return caid;
    }

    public void setCaid(String caid) {
        this.caid = caid;
    }

    public String getCmuid() {
        return cmuid;
    }

    public void setCmuid(String cmuid) {
        this.cmuid = cmuid;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public String getProvience() {
        return provience;
    }

    public void setProvience(String provience) {
        this.provience = provience;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOperaType() {
        return operaType;
    }

    public void setOperaType(String operaType) {
        this.operaType = operaType;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRealNameLeve() {
        return realNameLeve;
    }

    public void setRealNameLeve(String realNameLeve) {
        this.realNameLeve = realNameLeve;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public Date getCertExpireDate() {
        return certExpireDate;
    }

    public void setCertExpireDate(Date certExpireDate) {
        this.certExpireDate = certExpireDate;
    }

    public String getCertCopy() {
        return certCopy;
    }

    public void setCertCopy(String certCopy) {
        this.certCopy = certCopy;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
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

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
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

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CustCompanyDto [custCode=" + custCode + ", localName=" + localName + ", unitType=" + unitType
                + ", socialCreditId=" + socialCreditId + ", businessLicense=" + businessLicense + ", startCreateTime="
                + startCreateTime + ", endCreateTime=" + endCreateTime + ", status=" + status + "]";
    }

}

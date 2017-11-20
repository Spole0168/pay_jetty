/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * 客户信息实体对象 Creator : Spole Date : 2016年12月14日
 */
public class CorCust extends BaseEntity {
    private static final long serialVersionUID = -4731708415114232373L;

    private String id; // ID
    private String custCode; // CUST_CODE
    private String custType; // CUST_TYPE
    private String country; // COUNTRY
    private String provience;
    private String city;
    private String region;
    private String postcode; // IS_VALID
    private String localName; // LOCAL_NAME
    private String realNameLeve; // REAL_NAME_LEVE
    private String firstName; // FIRST_NAME
    private String lastName; // LAST_NAME
    private String certType; // CERT_TYPE
    private String certNum; // CERT_NUM
    private Date certExpireDate; // CERT_EXPIRE_DATE
    private String certCopy; // CERT_COPY
    private String status; // STATUS
    private Date regTime; // REG_TIME
    private String dataSource; // DATA_SOURCE
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
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

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "CorCust [id=" + id + ", custCode=" + custCode + ", custType=" + custType + ", country=" + country
                + ", provience=" + provience + ", city=" + city + ", region=" + region + ", localName=" + localName
                + ", realNameLeve=" + realNameLeve + ", firstName=" + firstName + ", lastName=" + lastName
                + ", certType=" + certType + ", certNum=" + certNum + ", certExpireDate=" + certExpireDate
                + ", certCopy=" + certCopy + ", status=" + status + ", regTime=" + regTime + ", dataSource="
                + dataSource + ", creator=" + creator + ", createTime=" + createTime + ", updator=" + updator
                + ", updateTime=" + updateTime + ", remark=" + remark + ", isValid=" + isValid + ", postcode="
                + postcode + "]";
    }

}

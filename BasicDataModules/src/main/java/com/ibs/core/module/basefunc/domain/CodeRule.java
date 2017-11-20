package com.ibs.core.module.basefunc.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class CodeRule extends BaseEntity{

    /**
     * 
     */
    private static final long serialVersionUID = 6464225396238863915L;
    
    private String id;
    private String codeType;
    private String prefix;
    private String isTimeMark;
    private Integer suffixLength;
    private String suffixCurrentValue;
    private String currentValue;
    private String status;
    private String creator;
    private Date createTime;
    private String updator;
    private Date updateTime;
    private String isValid;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCodeType() {
        return codeType;
    }
    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getIsTimeMark() {
        return isTimeMark;
    }
    public void setIsTimeMark(String isTimeMark) {
        this.isTimeMark = isTimeMark;
    }
    public Integer getSuffixLength() {
        return suffixLength;
    }
    public void setSuffixLength(Integer suffixLength) {
        this.suffixLength = suffixLength;
    }
    public String getSuffixCurrentValue() {
        return suffixCurrentValue;
    }
    public void setSuffixCurrentValue(String suffixCurrentValue) {
        this.suffixCurrentValue = suffixCurrentValue;
    }
    public String getCurrentValue() {
        return currentValue;
    }
    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
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

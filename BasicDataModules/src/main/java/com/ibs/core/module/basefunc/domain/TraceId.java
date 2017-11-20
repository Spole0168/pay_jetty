package com.ibs.core.module.basefunc.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @author Patch Cao
 * @date 2016/11/17
 * @version 1.0
 */

/*
 * 会员编号
 */
public class TraceId extends BaseEntity {

    private static final long serialVersionUID = 6025504224098957233L;

    // 主键
    private String id;

    // 编码类型
    private String codeType;

    // 编码前缀
    private String prefix;

    // 是否含有时间戳
    private String timeStamp;

    // 编码后缀长度
    private BigDecimal suffixLength;

    // 编码后缀下一次的值
    private String suffixCurrentValue;

    // 会员编号后缀当前值
    private String currentValue;

    // 状态
    private String status;

    // 创建者
    private String creator;

    // 创建时间
    private Timestamp createTime;

    // 修改人
    private String updator;

    // 修改时间
    private Timestamp updateTime;

    // 是否有效
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BigDecimal getSuffixLength() {
        return suffixLength;
    }

    public void setSuffixLength(BigDecimal suffixLength) {
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
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
        return "TraceId [id=" + id + ", codeType=" + codeType + ", prefix=" + prefix + ", timeStamp=" + timeStamp
                + ", suffixLength=" + suffixLength + ", suffixCurrentValue=" + suffixCurrentValue + ", currentValue="
                + currentValue + ", status=" + status + ", creator=" + creator + ", createTime=" + createTime
                + ", updator=" + updator + ", updateTime=" + updateTime + ", isValid=" + isValid + "]";
    }

}

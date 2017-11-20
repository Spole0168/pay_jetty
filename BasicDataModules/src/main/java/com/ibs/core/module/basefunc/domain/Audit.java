package com.ibs.core.module.basefunc.domain;

import java.util.Date;

import com.ibs.portal.framework.server.domain.BaseEntity;

public class Audit extends BaseEntity {

    private static final long serialVersionUID = -5378535519132306614L;
    private String id; // ID
    private String auditType; // AUDIT_TYPE
    private String sourceCode; // SOURCE_CODE
    private String status; // STATUS
    private String auditReason; // AUDIT_REASON
    private String creator; // CREATOR
    private Date createTime; // CREATE_TIME
    private String auditReasonCategory;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getAuditReason() {
        return auditReason;
    }
    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
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
	public String getAuditReasonCategory() {
		return auditReasonCategory;
	}
	public void setAuditReasonCategory(String auditReasonCategory) {
		this.auditReasonCategory = auditReasonCategory;
	}
}

package com.ibs.core.module.basefunc.dao;

import java.util.List;

import com.ibs.core.module.basefunc.domain.*;

public interface IAuditLogDao {
    /**
     * 插入审核日志
     * @param auditLog
     */
    public void saveAuditLog(AuditLog auditLog);
    
    public List<AuditLog> findByMerchantCode(String merchantCode);
    /**
     * 根据结算单查询审核信息
     * @param statementCode
     * @return
     */
    public Audit queryAudit(String statementCode);
}

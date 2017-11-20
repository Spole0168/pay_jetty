package com.ibs.core.module.basefunc.dao;

import com.ibs.core.module.basefunc.domain.Audit;
/**
 * Shangzhuzi
 * @author jh
 *
 */
public interface IAuditDao {
    /**
     *  Shangzhuzi
     * 处理审核信息
     * @param audit
     */
    public void saveOrUpdateAudit(Audit audit);
    /**
     * Shangzhuzi
     * @param audit
     * @return
     */
    public Audit getAuditByCondition(Audit audit);
    
    public Audit findAuditById(String id);
    /**
     * 根据结算单查询是否审核过
     * @param statementCode
     */
    Audit queryAudit(String statementCode);
    
}

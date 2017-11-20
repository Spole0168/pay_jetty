package com.ibs.core.module.basefunc.service;

import com.ibs.core.module.basefunc.domain.Audit;

public interface IAuditService {
    /**
     * 审核接口
     * @param audit
     */
    public void saveAudit(Audit audit);
    /**
     * 商户审核通过时，修改之前log表中的此商户的临时code为实际code
     * @param audit
     * @param temCode
     */
    public void updateMert(Audit audit,String temCode);
    /**
     * 根据结算单查询是否审核过
     * @param statementCode
     */
    Audit queryAudit(String statementCode);


}

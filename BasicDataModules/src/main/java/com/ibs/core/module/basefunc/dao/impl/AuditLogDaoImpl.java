package com.ibs.core.module.basefunc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.ibs.core.module.basefunc.dao.IAuditLogDao;
import com.ibs.core.module.basefunc.domain.Audit;
import com.ibs.core.module.basefunc.domain.AuditLog;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
public class AuditLogDaoImpl extends BaseEntityDao<AuditLog> implements IAuditLogDao {

    @Override
    public void saveAuditLog(AuditLog auditLog) {
        super.save(auditLog);
    }

    
    @Override
    public List<AuditLog> findByMerchantCode(String merchantCode) {
        return findByValue("from AuditLog m where m.sourceCode = ?", merchantCode);
    }


    @Override
    public Audit queryAudit(String statementCode) {
        String sql = " SELECT * FROM T_COR_AUDIT WHERE SOURCE_CODE = '" + statementCode + "' AND AUDIT_TYPE = '"
                + CorConstants.AUDIT_TYPE_SETTLE_ORDER + "'";
        SQLQuery query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.addEntity(Audit.class);
        Audit audit = (Audit) query.uniqueResult();
        return audit;
    }
}

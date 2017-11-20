package com.ibs.core.module.basefunc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

import com.ibs.core.module.basefunc.dao.IAuditDao;
import com.ibs.core.module.basefunc.domain.Audit;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
public class AuditDaoImpl extends BaseEntityDao<Audit> implements IAuditDao {

    @Override
    public void saveOrUpdateAudit(Audit audit) {
        super.saveOrUpdate(audit);
    }

    @Override
    public Audit getAuditByCondition(Audit audit) {
        Criteria criteria = super.getSession().createCriteria(Audit.class);
        if(null!=audit && null!=audit.getAuditType()&&!"".equals(audit.getAuditType().trim())){
            criteria.add(Restrictions.eq("auditType", audit.getAuditType()));
        }
        if(null!=audit && null!=audit.getSourceCode()&&!"".equals(audit.getSourceCode().trim())){
            criteria.add(Restrictions.eq("sourceCode", audit.getSourceCode()));
        }
        List<Audit> list = criteria.list();
        if(null!=list&&list.size()>0){
            return list.get(0);     
        }
        return null;
    }
    public Audit findAuditById(String id){
        return load(id);
    }

    @Override
    public Audit queryAudit(String statementCode) {
        String sql = "select * from T_COR_AUDIT where SOURCE_CODE = '" + statementCode + "' and AUDIT_TYPE = '"
                + CorConstants.AUDIT_TYPE_SETTLE_ORDER + "'";
        SQLQuery query = super.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.addEntity(Audit.class);
        Audit audit = (Audit) query.uniqueResult();
        return audit;
    }

}

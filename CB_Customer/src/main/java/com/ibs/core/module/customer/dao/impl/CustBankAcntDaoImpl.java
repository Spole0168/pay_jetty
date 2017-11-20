package com.ibs.core.module.customer.dao.impl;

import com.ibs.core.module.customer.dao.ICustBankAcntDao;
import com.ibs.core.module.customer.domain.CustBankAcnt;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

public class CustBankAcntDaoImpl extends BaseEntityDao<CustBankAcnt> implements ICustBankAcntDao {

    @Override
    public void saveOrUpdate(CustBankAcnt custBankAcnt) {
        super.saveOrUpdate(custBankAcnt);
    }
}

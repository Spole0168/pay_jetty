package com.ibs.core.module.customer.dao.impl;

import java.util.List;

import com.ibs.core.module.customer.dao.ICorMertBankAcntChgDao;
import com.ibs.core.module.customer.domain.CorMertBankAcntChg;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;

public class CorMertBankAcntChgDaoImpl extends BaseEntityDao<CorMertBankAcntChg> implements ICorMertBankAcntChgDao {

    public void saveOrUpdate(CorMertBankAcntChg corMertBankAcntChg) {

        super.saveOrUpdate(corMertBankAcntChg);
    }

    public CorMertBankAcntChg findCorMertBankAcntChg(String merchantCode) {

        String hql = " from CorMertBankAcntChg where isValid ='" + CorConstants.DATA_DICT__IS_VALID + "' and merchantCode = '"
                + merchantCode + "'";
        List<Object> list = findByHql(hql, null, null);
        if (list != null && list.size() > 0) {
            return (CorMertBankAcntChg) list.get(0);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        super.remove(id);
    }

}

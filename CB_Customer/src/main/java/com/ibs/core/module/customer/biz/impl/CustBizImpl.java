package com.ibs.core.module.customer.biz.impl;

import com.ibs.core.module.customer.biz.ICustBiz;
import com.ibs.core.module.customer.dao.ICustDao;
import com.ibs.core.module.customer.domain.CorCust;
import com.ibs.portal.framework.server.biz.BaseBiz;

public class CustBizImpl extends BaseBiz implements ICustBiz {
    private ICustDao custDao;

    public void setCustDao(ICustDao custDao) {
        this.custDao = custDao;
    }

    @Override
    public CorCust getCustByCustCode(String custCode) {
        return custDao.getCustByCustCode(custCode);
    }

    @Override
    public CorCust getCustByLocalName(String localName) {
        return custDao.getCustByLocalName(localName);
    }
}

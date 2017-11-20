package com.ibs.core.module.customer.biz.impl;

import com.ibs.core.module.customer.biz.ICorMertBankAcntChgBiz;
import com.ibs.core.module.customer.dao.ICorMertBankAcntChgDao;
import com.ibs.core.module.customer.domain.CorMertBankAcntChg;
import com.ibs.portal.framework.server.biz.BaseBiz;

public class CorMertBankAcntChgBizImpl extends BaseBiz implements ICorMertBankAcntChgBiz {

    private ICorMertBankAcntChgDao corMertBankAcntChgDao;

    public ICorMertBankAcntChgDao getCorMertBankAcntChgDao() {
        return corMertBankAcntChgDao;
    }

    public void setCorMertBankAcntChgDao(ICorMertBankAcntChgDao corMertBankAcntChgDao) {
        this.corMertBankAcntChgDao = corMertBankAcntChgDao;
    }

    public CorMertBankAcntChg findCorMertBankAcntChg(String merchantCode) {
        return corMertBankAcntChgDao.findCorMertBankAcntChg(merchantCode);
    }

}

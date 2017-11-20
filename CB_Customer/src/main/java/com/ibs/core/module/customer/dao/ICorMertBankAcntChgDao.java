package com.ibs.core.module.customer.dao;

import com.ibs.core.module.customer.domain.CorMertBankAcntChg;

public interface ICorMertBankAcntChgDao {

    public void saveOrUpdate(CorMertBankAcntChg corMertBankAcntChg);

    public CorMertBankAcntChg findCorMertBankAcntChg(String merchantCode);

    public void delete(String id);

}

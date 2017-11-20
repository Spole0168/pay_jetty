/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.customer.dao;

import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_BANK_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorMertBankAcntDao {

    public IPage<CorMertBankAcnt> findCorMertBankAcntByPage(QueryPage queryPage);

    public void saveOrUpdate(CorMertBankAcnt corMertBankAcnt);

    public CorMertBankAcnt load(String id);

    public CorMertBankAcnt findCorMertBankAcnt(String merchantCode);
    
    public CorMertBankAcnt findCorMertBankAcntInfo(CorMertBankAcnt corMertBankAcnt);
    
    public CorMertBankAcnt getDefault(String merchantCode);

    /**
     * 根据商户查询商户银行账户表
     * @param merchantCode
     * @return
     */
    CorMertBankAcnt getCountryByMerChantCode(String merchantCode);
}

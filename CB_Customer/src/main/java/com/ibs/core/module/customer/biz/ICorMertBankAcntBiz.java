/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.biz;

import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_BANK_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public interface ICorMertBankAcntBiz {

    public IPage<CorMertBankAcnt> findCorMertBankAcntByPage(QueryPage queryPage);

    public CorMertBankAcnt getCorMertBankAcntById(String id);

    public CorMertBankAcnt saveCorMertBankAcnt(CorMertBankAcnt object);

    public CorMertBankAcnt updateCorMertBankAcnt(CorMertBankAcnt object);

    public void exportCorMertBankAcnt(ExportSetting exportSetting);

    public CorMertBankAcnt find(String merchantCode);
    public CorMertBankAcnt findCorMertBankAcntInfo(CorMertBankAcnt corMertBankAcnt);

}

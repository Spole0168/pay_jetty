package com.ibs.core.module.customer.biz;

import com.ibs.core.module.customer.domain.CorMertBankAcntChg;
import com.ibs.core.module.customer.domain.MerchantChg;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IMerchantChgBiz {

    public MerchantDto getMerchantChgBymertCode(String merchantCode);

    public MerchantChg getMerchantChg(String merchantCode);

    public IPage<MerchantDto> findMerchantChgListByPage(QueryPage queryPage, MerchantDto merchantDto);

    public MerchantChg findMerchatChgById(String id);

    void updateMerchantInfo(MerchantChg merchantChg, CorMertBankAcntChg corMertBankAcntChg, String id, String userName);

    void review(MerchantChg merchantChg, MerchantDto merchantDto, String id, String userName);

    public void delete(String id);
    public MerchantDto getMerchantChgAut(String merchantCode);
}

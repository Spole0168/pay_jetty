package com.ibs.core.module.customer.dao;

import com.ibs.core.module.customer.domain.MerchantChg;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IMerchantChgDao {

    public MerchantDto getMerchantChgBymertCode(String merchantCode);

    public void saveOrUpdate(MerchantChg merchantChg);

    public MerchantChg getMerchantChg(String merchantCode);

    public IPage<MerchantDto> findMerchantChgListByPage(QueryPage queryPage, MerchantDto merchantDto);

    public MerchantChg findMerchatChgById(String id);

    public void delete(String id);
    public MerchantDto getMerchantChgAut(String merchantCode);
}

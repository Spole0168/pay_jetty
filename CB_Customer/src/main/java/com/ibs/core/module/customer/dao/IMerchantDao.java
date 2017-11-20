package com.ibs.core.module.customer.dao;

import java.util.List;

import com.ibs.core.module.customer.domain.Merchant;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IMerchantDao {

    IPage<MerchantDto> findMerchantListByPage(QueryPage queryPage, MerchantDto obaDto);

    MerchantDto getMerchantDetailInfoById(MerchantDto md);

    void SaveOrUpdateMechant(Merchant merchant);

    Merchant findMerchatById(String id);

    List<OptionObjectPair> getBankName(String cityCode);

    List<OptionObjectPair> getBankBranchName(String bankCode, String cityCode);

    // 根据商户号查找商户
    List<Merchant> findByMerchantCode(String merchantCode);

    MerchantDto findAudit(String merchantCode);

    List<Merchant> findAll();
    List<OptionObjectPair> getCategoryInfo(String categoryCode,String mccCode);
}

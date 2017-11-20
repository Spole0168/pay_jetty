package com.ibs.core.module.customer.biz;

import java.util.List;

import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.core.module.customer.domain.Merchant;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.OptionObjectPair;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface IMerchatBiz {

    public IPage<MerchantDto> findMerchantListByPage(QueryPage queryPage, MerchantDto merchantDto);

    public MerchantDto getMerchantDetailInfoById(String merchantId);

    void saveMerchantInfo(Merchant merchant, CorMertBankAcnt corMertBankAcnt, String userName);

    void review(Merchant merchant, MerchantDto merchantDto, String id, String userName);

    Merchant findMerchatById(String id);

    void updateMerchantInfo(Merchant merchant, CorMertBankAcnt corMertBankAcnt, String id, String userName);

    List<OptionObjectPair> getCountryInfo();

    List<OptionObjectPair> getProvinceInfo(String countryCode);

    List<OptionObjectPair> getCityInfo(String provinceCode);

    List<OptionObjectPair> getBankName(String cityCode);

    List<OptionObjectPair> getBankBranchName(String bankCode, String cityCode);
    List<OptionObjectPair> getCategoryInfo(String categoryCode,String mccCode);

    MerchantDto MerchantDtoByMerchantCode(String merchantCode);

    // 根据商户号查找商户名
    Merchant findMerchantName(String merchantCode);

    void saveCorAccount(Merchant merchant, String userName);

    void mertIscheck(MerchantDto merchantDto);

    void saveChange(Merchant merchant, CorMertBankAcnt corMertBankAcnt, String userName);

    void saveChangeAudit(String id, String userName);

    void saveChangeBankAcct(String id, CorMertBankAcnt corMertBankAcnt, String userName);
    public MerchantDto getMerchant(MerchantDto md);
}

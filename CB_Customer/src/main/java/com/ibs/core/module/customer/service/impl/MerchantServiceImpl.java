package com.ibs.core.module.customer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.core.module.customer.dao.ICorMertBankAcntDao;
import com.ibs.core.module.customer.dao.ICorMertRateDao;
import com.ibs.core.module.customer.dao.IMerchantDao;
import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.core.module.customer.domain.CorMertRate;
import com.ibs.core.module.customer.domain.CorMertRateResult;
import com.ibs.core.module.customer.domain.Merchant;
import com.ibs.core.module.customer.dto.CorMertRateDto;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.core.module.customer.service.IMerchantService;
import com.ibs.portal.framework.server.service.BaseService;
import com.ibs.portal.framework.util.DateUtils;

public class MerchantServiceImpl extends BaseService implements IMerchantService {

    private IMerchantDao merchantDao;

    private ICorMertRateDao corMertRateDao;
    private ICorMertBankAcntDao corMertBankAcntDao;

    public ICorMertBankAcntDao getCorMertBankAcntDao() {
        return corMertBankAcntDao;
    }

    public void setCorMertBankAcntDao(ICorMertBankAcntDao corMertBankAcntDao) {
        this.corMertBankAcntDao = corMertBankAcntDao;
    }

    public IMerchantDao getMerchantDao() {
        return merchantDao;
    }

    public void setMerchantDao(IMerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }

    public ICorMertRateDao getCorMertRateDao() {
        return corMertRateDao;
    }

    public void setCorMertRateDao(ICorMertRateDao corMertRateDao) {
        this.corMertRateDao = corMertRateDao;
    }

    @Override
    public CorMertRateResult queryMertRate(String merchantCode, String productCode, String cardType,
            String cardProperty, String settType) {
        logger.info("enter MerchantServiceImpl.queryMertRate # merchantCode=" + merchantCode + ",  productCode=" + productCode + ", cardType=" + cardType + ", cardProperty=" + cardProperty + ", settType=" + settType);
        CorMertRateDto corMertRateDto = new CorMertRateDto();
        if (null != merchantCode) {
            corMertRateDto.setMerchantCode(merchantCode);
        }
        corMertRateDto.setProductCode(productCode);
        corMertRateDto.setCardType(cardType);
        //corMertRateDto.setCardProperty(cardProperty);
        corMertRateDto.setSettType(settType);
        List<CorMertRate> mertRateList = corMertRateDao.getCorMertRateDtoByConditionAND(corMertRateDto);
        logger.info("=======mertRateList="+mertRateList);
        Date nowDate = new Date();
        CorMertRate mertRate = null;
        if (null == mertRateList) {
            //通配规则查询
            merchantCode = null;
            corMertRateDto.setMerchantCode(merchantCode);
            mertRateList = corMertRateDao.getCorMertRateDtoByConditionAND(corMertRateDto);
        }else{
            for(CorMertRate corMertRate1 : mertRateList){
                if (DateUtils.getDaysDiff(nowDate, corMertRate1.getExpireDate()) > 0
                        && DateUtils.getDaysDiff(nowDate, corMertRate1.getEffectDate()) < 0) {
                    //满足条件
                    mertRate = corMertRate1;
                    logger.info("===[queryMertRate]mertRate="+mertRate);
                    //费率对象
                    if (mertRate != null) {
                        return new CorMertRateResult(mertRate);
                    }
                }
               
            }
            //时间无效 通配规则查询
            logger.info("===[queryMertRate]时间不在有效期内，走else查询通配费率");
            merchantCode = null;
            corMertRateDto.setMerchantCode(merchantCode);
            mertRateList = corMertRateDao.getCorMertRateDtoByConditionAND(corMertRateDto); 
            logger.info("===[queryMertRate]时间不在有效期内，走else查询通配费率结束mertRateList="+mertRateList);
           
        }
        
        //费率对象
        if (null != mertRateList && mertRateList.size() > 0) {
            for (CorMertRate rate : mertRateList) {
                logger.info("===[rate]="+rate);
                if ( DateUtils.getDaysDiff(nowDate, rate.getExpireDate()) > 0
                        && DateUtils.getDaysDiff(nowDate, rate.getEffectDate()) < 0) {
                    mertRate = rate;
                    logger.info("===[queryMertRate]mertRate="+mertRate);
                }
            }
        }
        if (mertRate != null) {
            return new CorMertRateResult(mertRate);
        }
        return null;
    }

    @Override
    public String queryMertStatus(String merchantCode) {
        logger.debug("enter MerchantServiceImpl.queryMertStatus # merchantCode=" + merchantCode);
        StringBuffer sb = new StringBuffer();
        if (merchantCode != null) {
            List<Merchant> merchantList = merchantDao.findByMerchantCode(merchantCode);
            if (null != merchantList && merchantList.size() > 0) {
                Merchant merchant = merchantList.get(0);
                sb.append(merchant.getCustCode()).append("#");
                sb.append(merchant.getIsCheckin()).append("#");
                sb.append(merchant.getIsCheckout());
            }
        }
        return sb.toString();
    }

    @Override
    public Map<String, String> queryMerchantInfo(String merchantCode) {
        logger.debug("enter MerchantServiceImpl.queryMerchantInfo # merchantCode=" + merchantCode);
        Map<String, String> merInfoMap = new HashMap<String, String>();
        if (merchantCode != null) {
            MerchantDto mdto = new MerchantDto();
            mdto.setMerchantCode(merchantCode);
            MerchantDto merchanDto = merchantDao.getMerchantDetailInfoById(mdto);
            if (null != merchanDto) {
                merInfoMap.put("custCode", merchanDto.getCustCode());
                merInfoMap.put("custName", merchanDto.getCustName());
                merInfoMap.put("merchantName", merchanDto.getMerchantName());
                merInfoMap.put("riskLevel", merchanDto.getRiskLevel());
            }
        }
        return merInfoMap;
    }

    @Override
    public String queryMertName(String merchantCode) {
        logger.debug("enter MerchantServiceImpl.queryMertName # merchantCode=" + merchantCode);
        String merchantName = "";
        if (merchantCode != null) {
            List<Merchant> merchantList = merchantDao.findByMerchantCode(merchantCode);
            if (null != merchantList && merchantList.size() > 0) {
                Merchant merchant = merchantList.get(0);
                merchantName = merchant.getMerchantName();
            }
        }
        return merchantName;
    }

    @Override
    public List<Merchant> queryAllMerchantInfo() {
        logger.debug("enter MerchantServiceImpl.queryAllMerchantInfo ");
        return merchantDao.findAll();
    }

    @Override
    public Merchant queryMerchant(String merchantCode) {
        logger.debug("enter MerchantServiceImpl.queryMerchant # merchantCode=" + merchantCode);
        if (merchantCode != null) {
            List<Merchant> merchantList = merchantDao.findByMerchantCode(merchantCode);
            if (null != merchantList && merchantList.size() > 0) {
                return merchantList.get(0);
            }
        }
        return null;
    }

    @Override
    public String queryMertPhoneNum(String merchantCode) {
        logger.debug("enter MerchantServiceImpl.queryMertPhoneNum # merchantCode=" + merchantCode);
        
        String phoneNum = "";
        if (merchantCode != null) {
            List<Merchant> merchantList = merchantDao.findByMerchantCode(merchantCode);
            if (null != merchantList && merchantList.size() > 0) {
                return merchantList.get(0).getBusinessResponsePhone();
            }
        }
        return phoneNum;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibs.core.module.customer.service.IMerchantService#queryMerDtoInfo(com
     * .ibs.core.module.customer.dto.MerchantDto)
     */
    @Override
    public MerchantDto queryMerDtoInfo(MerchantDto md) {
        logger.debug("enter MerchantServiceImpl.queryMerDtoInfo # MerchantDto=" + md);
        
        return merchantDao.getMerchantDetailInfoById(md);
    }

    @Override
    public CorMertBankAcnt findCorMertBankAcntInfo(CorMertBankAcnt corMertBankAcnt) {
        logger.debug("enter MerchantServiceImpl.findCorMertBankAcntInfo # ");
        
        return corMertBankAcntDao.findCorMertBankAcntInfo(corMertBankAcnt);
    }

    @Override
    public List<CorMertRate> getCorMertRateDto(CorMertRateDto corMertRate) {
        logger.debug("enter MerchantServiceImpl.getCorMertRateDto ");
        
        List<CorMertRate> mertRateList = corMertRateDao.getCorMertRateDtoByConditionAND(corMertRate);
        Date nowDate = new Date();
        List<CorMertRate> mertRate = new ArrayList<CorMertRate>();
        if (null != mertRateList && mertRateList.size() > 0) {
            for (CorMertRate rate : mertRateList) {
                if (rate.getMerchantCode() != null && corMertRate.getMerchantCode().equals(rate.getMerchantCode())
                        && DateUtils.getDaysDiff(rate.getEffectDate(), nowDate) >= 0
                        && DateUtils.getDaysDiff(nowDate, rate.getExpireDate()) > 0) {
                    mertRate.add(rate);
                }

            }
        }
        return mertRate;

    }

	@Override
	public CorMertBankAcnt getDefaultBankAcnt(String merchantCode) {
		logger.info("enter into MerchantServiceImpl.getDefaultBankAcnt...");
		return corMertBankAcntDao.getDefault(merchantCode);
	}

    @Override
    public CorMertBankAcnt getCountryByMerChantCode(String merchantCode) {
        logger.info("enter into MerchantServiceImpl.getCountryByMerChantCode...");
        CorMertBankAcnt corMertBankAcnt = corMertBankAcntDao.getCountryByMerChantCode(merchantCode);
        logger.info("===[getCountryByMerChantCode]查询结果...corMertBankAcnt="+corMertBankAcnt);
        return corMertBankAcnt;
    }
}

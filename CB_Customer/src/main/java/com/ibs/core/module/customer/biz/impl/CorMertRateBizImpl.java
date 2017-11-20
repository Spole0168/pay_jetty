/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.biz.impl;

import java.util.Date;
import java.util.List;

import com.ibs.core.module.basefunc.service.ICodeRuleService;
import com.ibs.core.module.customer.biz.ICorMertRateBiz;
import com.ibs.core.module.customer.dao.ICorMertRateDao;
import com.ibs.core.module.customer.dao.IMerchantDao;
import com.ibs.core.module.customer.domain.CorMertRate;
import com.ibs.core.module.customer.domain.Merchant;
import com.ibs.core.module.customer.dto.CorMertRateDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.exception.BizException;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;
import com.ibs.portal.framework.util.DateUtils;

public class CorMertRateBizImpl extends BaseBiz implements ICorMertRateBiz {

    private ICorMertRateDao corMertRateDao;
    private IMerchantDao merchantDao;
    private ICodeRuleService codeRuleService;

    public ICorMertRateDao getCorMertRateDao() {
        return corMertRateDao;
    }

    public void setCorMertRateDao(ICorMertRateDao corMertRateDao) {
        this.corMertRateDao = corMertRateDao;
    }

    public ICodeRuleService getCodeRuleService() {
        return codeRuleService;
    }

    public void setCodeRuleService(ICodeRuleService codeRuleService) {
        this.codeRuleService = codeRuleService;
    }

    public IMerchantDao getMerchantDao() {
        return merchantDao;
    }

    public void setMerchantDao(IMerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }

    @Override
    public IPage<CorMertRate> findCorMertRateByPage(QueryPage queryPage) {
        logger.info("entering action::CorMertRateBizImpl.findCorMertRateByPage()...");
        return corMertRateDao.findCorMertRateByPage(queryPage);
    }

    @Override
    public IPage<CorMertRateDto> findCorMertRateDtoBySql(QueryPage queryPage, CorMertRateDto corMertRateDto) {
        return corMertRateDao.findCorMertRateDtoBySql(queryPage, corMertRateDto);
    }

    @Override
    public CorMertRateDto getCorMertRateDtoById(String id) {
        CorMertRateDto corMertRateDto = new CorMertRateDto();
        corMertRateDto.setId(id);
        return corMertRateDao.getCorMertRateDtoByCondition(corMertRateDto);
    }

    @Override
    public void opera(CorMertRateDto corMertRateDto) {
        if (null != corMertRateDto) {
            this.saveOrUpdateInfo(corMertRateDto);
        }
    }

    private void saveOrUpdateInfo(CorMertRateDto corMertRateDto) {
        CorMertRate tem = new CorMertRate();
        if (CorConstants.OPERA_ADD.equals(corMertRateDto.getOperaType())) {
            String mertRateCode = "";

            try {
                boolean flag = (corMertRateDto.getMerchantCode() != null && corMertRateDto.getMerchantCode().length()!=0) ? true : false;
                mertRateCode = codeRuleService.getFeeCode(flag, corMertRateDto.getProductCode());
            } catch (Exception e) {
                logger.error("调用生成费率编码接口失败", e);
                throw new BizException("调用生成费率编码接口失败: ", e);
            }
            BeanUtils.copyObjectTypeProperties(tem, corMertRateDto);
            tem.setCreateTime(new Date());
            tem.setCreator(corMertRateDto.getUpdator());
            tem.setMertRateCode(mertRateCode);
            tem.setId(null);
        } else {
            tem = corMertRateDao.load(corMertRateDto.getId());
            BeanUtils.copyObjectTypeProperties(tem, corMertRateDto);
        }
        corMertRateDao.saveOrUpdate(tem);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibs.core.module.customer.biz.ICorMertRateBiz#getMerchantName(java.
     * lang.String)
     */
    @Override
    public String getMerchantName(String id) {
        List<Merchant> list = merchantDao.findByMerchantCode(id);
        if (null != list && list.size() > 0) {
            return list.get(0).getMerchantName();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ibs.core.module.customer.biz.ICorMertRateBiz#existsInFeeRateInfo(com.
     * ibs.core.module.customer.dto.CorMertRateDto)
     */
    @Override
    public boolean existsInFeeRateInfo(CorMertRateDto corMertRateDto) {
        /*
         * 2) 系统检查新增手续费方案信息是否与系统中已存在的手续费方案信息冲突（设新增的记录手续费编码为FEE201611110002）： a)
         * 判断系统中是否存在与FEE2016111110002有相同的【商户编号】+【产品类型】+【产品子类型】+【结算方式】的记录，
         * 不存在则新增一该条手续费规则；存在则继续判断（设存在的这条记录手续费编码为FEE201611110001，生效日期为A,失效日期为B）
         * b) 判断FEE201611110001是否失效，是则新增FEE2016111110002这条记录，不是则继续判断 c)
         * 判断FEE2016111110002的【生效日期】和【失效日期】是否落在区间[A,B]中，是则提示“存在冲突的手续费规则”，
         * 否则新增该条手续费规则
         * 
         */
        CorMertRateDto corMertRateCondition = new CorMertRateDto();
        BeanUtils.copyObjectTypeProperties(corMertRateCondition, corMertRateDto);
        corMertRateCondition.setId(null);

        List<CorMertRate> list = corMertRateDao.getCorMertRateDtoByConditionAND(corMertRateCondition);
        if (null == list || list.size() == 0) {
            return false;
        } else if (null != list && list.size() > 0) {
            Date newRateStartDate = corMertRateDto.getEffectDate();
            Date newRateEndDate = corMertRateDto.getExpireDate();

            for (CorMertRate temp : list) {
                Date rateStartDate = temp.getEffectDate();
                Date rateEndDate = temp.getExpireDate();
                String merchantCode = temp.getMerchantCode();

                if (corMertRateDto.getId() != null && corMertRateDto.getId().equals(temp.getId())) {
                    continue;
                }

                if (merchantCode != null && corMertRateDto.getMerchantCode() != null
                        && !merchantCode.equals(corMertRateDto.getMerchantCode())) {
                    continue;
                }

                if (DateUtils.getDaysDiff(rateStartDate, newRateStartDate) >= 0
                        && DateUtils.getDaysDiff(rateEndDate, newRateStartDate) < 0) {
                    return true;
                }

                if (DateUtils.getDaysDiff(rateStartDate, newRateEndDate) >= 0
                        && DateUtils.getDaysDiff(rateEndDate, newRateEndDate) < 0) {
                    return true;
                }

                if (DateUtils.getDaysDiff(rateStartDate, newRateStartDate) <= 0
                        && DateUtils.getDaysDiff(rateEndDate, newRateEndDate) >= 0) {
                    return true;
                }
            }

        }

        return false;
    }

}

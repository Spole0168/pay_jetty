package com.ibs.core.module.customer.biz.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibs.core.module.basefunc.domain.Audit;
import com.ibs.core.module.basefunc.service.IAuditService;
import com.ibs.core.module.customer.biz.IMerchantChgBiz;
import com.ibs.core.module.customer.biz.IMerchatBiz;
import com.ibs.core.module.customer.dao.ICorMertBankAcntChgDao;
import com.ibs.core.module.customer.dao.ICorMertBankAcntDao;
import com.ibs.core.module.customer.dao.IMerchantChgDao;
import com.ibs.core.module.customer.dao.IMerchantDao;
import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.core.module.customer.domain.CorMertBankAcntChg;
import com.ibs.core.module.customer.domain.Merchant;
import com.ibs.core.module.customer.domain.MerchantChg;
import com.ibs.core.module.customer.dto.MerchantDto;
import com.ibs.core.module.mdmbasedata.common.CorConstants;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;

public class MerchantChgBizImpl extends BaseBiz implements IMerchantChgBiz {
    private IMerchantChgDao merchantChgDao;
    private IMerchantDao merchantDao;
    private IMerchatBiz merchantBiz;
    private ICorMertBankAcntChgDao corMertBankAcntChgDao;
    private ICorMertBankAcntDao corMertBankAcntDao;
    @Autowired
    private IAuditService auditService;

    public IMerchatBiz getMerchantBiz() {
        return merchantBiz;
    }

    public void setMerchantBiz(IMerchatBiz merchantBiz) {
        this.merchantBiz = merchantBiz;
    }

    public IMerchantDao getMerchantDao() {
        return merchantDao;
    }

    public void setMerchantDao(IMerchantDao merchantDao) {
        this.merchantDao = merchantDao;
    }

    public ICorMertBankAcntDao getCorMertBankAcntDao() {
        return corMertBankAcntDao;
    }

    public void setCorMertBankAcntDao(ICorMertBankAcntDao corMertBankAcntDao) {
        this.corMertBankAcntDao = corMertBankAcntDao;
    }

    public IAuditService getAuditService() {
        return auditService;
    }

    public void setAuditService(IAuditService auditService) {
        this.auditService = auditService;
    }

    public IMerchantChgDao getMerchantChgDao() {
        return merchantChgDao;
    }

    public void setMerchantChgDao(IMerchantChgDao merchantChgDao) {
        this.merchantChgDao = merchantChgDao;
    }

    public ICorMertBankAcntChgDao getCorMertBankAcntChgDao() {
        return corMertBankAcntChgDao;
    }

    public void setCorMertBankAcntChgDao(ICorMertBankAcntChgDao corMertBankAcntChgDao) {
        this.corMertBankAcntChgDao = corMertBankAcntChgDao;
    }

    @Override
    public MerchantDto getMerchantChgBymertCode(String merchantCode) {

        return merchantChgDao.getMerchantChgBymertCode(merchantCode);
    }

    @Override
    public MerchantChg getMerchantChg(String merchantCode) {

        return merchantChgDao.getMerchantChg(merchantCode);
    }

    @Override
    public IPage<MerchantDto> findMerchantChgListByPage(QueryPage queryPage, MerchantDto merchantDto) {
        return merchantChgDao.findMerchantChgListByPage(queryPage, merchantDto);
    }

    public MerchantChg findMerchatChgById(String id) {

        return merchantChgDao.findMerchatChgById(id);
    }

    // 修改
    public void updateMerchantInfo(MerchantChg merchantChg, CorMertBankAcntChg corMertBankAcntChg, String id,
            String userName) {

        MerchantChg newMerchantChg = merchantChgDao.findMerchatChgById(id);
        // 商户银行账户变更表对应的商户号为id
        CorMertBankAcntChg newCorMertBankAcntChg = corMertBankAcntChgDao.findCorMertBankAcntChg(id);

        BeanUtils.copyObjectTypeProperties(newMerchantChg, merchantChg);
        BeanUtils.copyObjectTypeProperties(newCorMertBankAcntChg, corMertBankAcntChg);
        newMerchantChg.setUpdator(userName);
        newMerchantChg.setUpdateTime(new Date());

        newCorMertBankAcntChg.setId(newCorMertBankAcntChg.getId());
        newCorMertBankAcntChg.setUpdator(userName);
        newCorMertBankAcntChg.setUpdateTime(new Date());

        Audit neAaudit = new Audit();
        neAaudit.setSourceCode(id);
        neAaudit.setCreateTime(new Date());
        neAaudit.setCreator(userName);
        neAaudit.setStatus(CorConstants.DATA_DICT__CHECK_STATUS_UNCHECK);
        neAaudit.setAuditType(CorConstants.AUDIT_TYPE_MERCHANT);
        merchantChgDao.saveOrUpdate(newMerchantChg);
        corMertBankAcntChgDao.saveOrUpdate(newCorMertBankAcntChg);
        auditService.saveAudit(neAaudit);

    }

    // 审核
    @Override
    public void review(MerchantChg merchantChg, MerchantDto merchantDto, String id, String userName) {
        Audit neAaudit = new Audit();
        MerchantDto tempMerchantDto = new MerchantDto();
        MerchantChg newMerchantChg = merchantChgDao.findMerchatChgById(id);
        if (newMerchantChg != null) {
            Merchant merchant = merchantBiz.findMerchantName(newMerchantChg.getMerchantCode());
            CorMertBankAcnt corMertBankAcnt = corMertBankAcntDao.findCorMertBankAcnt(newMerchantChg.getMerchantCode());
            CorMertBankAcntChg newCorMertBankAcntChg = corMertBankAcntChgDao.findCorMertBankAcntChg(id);

            if (CorConstants.DATA_DICT__CHECK_STATUS_CHECK_SUCCESS.equals(merchantChg.getStatus())) {
                // 临时存储原有商户以及商户银行表里面的创建时间及ID
                tempMerchantDto.setCreator(merchant.getCreator());
                tempMerchantDto.setCreateTime(merchant.getCreateTime());
                tempMerchantDto.setUpdator(corMertBankAcnt.getCreator());
                tempMerchantDto.setUpdateTime(corMertBankAcnt.getCreateTime());
                tempMerchantDto.setId(merchant.getId());
                tempMerchantDto.setAccId(corMertBankAcnt.getId());
                tempMerchantDto.setMerchantCode(merchant.getMerchantCode());
                // 更新商户商户变更表
                newMerchantChg.setUpdateTime(new Date());
                newMerchantChg.setUpdator(userName);
                newMerchantChg.setStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID);
                newMerchantChg.setRiskLevel(merchantChg.getRiskLevel());
                newMerchantChg.setInitialDeposit(merchantChg.getInitialDeposit());
                merchantChgDao.saveOrUpdate(newMerchantChg);

                BeanUtils.copyObjectTypeProperties(merchant, newMerchantChg);
                BeanUtils.copyObjectTypeProperties(corMertBankAcnt, newCorMertBankAcntChg);
                // 更新商户表
                merchant.setId(tempMerchantDto.getId());
                merchant.setCreateTime(tempMerchantDto.getCreateTime());
                merchant.setCreator(tempMerchantDto.getCreator());
                merchant.setUpdator(userName);
                merchant.setUpdateTime(new Date());
                merchant.setStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID);
                merchantDao.SaveOrUpdateMechant(merchant);
                // 更新商户银行账户变更表
                newCorMertBankAcntChg.setUpdateTime(new Date());
                newCorMertBankAcntChg.setUpdator(userName);
                newCorMertBankAcntChg.setStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID);
                corMertBankAcntChgDao.saveOrUpdate(newCorMertBankAcntChg);

                // 更新商户银行账户表
                corMertBankAcnt.setId(tempMerchantDto.getAccId());
                corMertBankAcnt.setMerchantCode(tempMerchantDto.getMerchantCode());
                corMertBankAcnt.setCreator(tempMerchantDto.getUpdator());
                corMertBankAcnt.setCreateTime(tempMerchantDto.getUpdateTime());
                corMertBankAcnt.setUpdator(userName);
                corMertBankAcnt.setUpdateTime(new Date());
                corMertBankAcnt.setStatus(CorConstants.DATA_DICT__CORE_CUST_STATUS_VALID);
                corMertBankAcntDao.saveOrUpdate(corMertBankAcnt);
            }
            neAaudit.setStatus(merchantChg.getStatus());
            neAaudit.setSourceCode(id);
            neAaudit.setCreateTime(new Date());
            neAaudit.setCreator(userName);
            neAaudit.setAuditType(CorConstants.AUDIT_TYPE_MERCHANT);
            ;
            if (CorConstants.DATA_DICT__CHECK_STATUS_CHECK_FAIL.equals(merchantChg.getStatus())) {
                neAaudit.setAuditReason(merchantDto.getReviewFailureReasons());

            }
            auditService.saveAudit(neAaudit);

        }

    }

    public void delete(String id) {
        CorMertBankAcntChg newCorMertBankAcntChg = corMertBankAcntChgDao.findCorMertBankAcntChg(id);
        merchantChgDao.delete(id);
        corMertBankAcntChgDao.delete(newCorMertBankAcntChg.getId());
    }

    public MerchantDto getMerchantChgAut(String merchantCode) {
        return merchantChgDao.getMerchantChgAut(merchantCode);
    }
}

/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.customer.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.customer.biz.ICorMertBankAcntBiz;
import com.ibs.core.module.customer.dao.ICorMertBankAcntDao;
import com.ibs.core.module.customer.domain.CorMertBankAcnt;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_COR_MERT_BANK_ACNT
 * @modify		: your comments goes here (author,date,reason).
 */
public class CorMertBankAcntBizImpl extends BaseBiz implements ICorMertBankAcntBiz {

    private ICorMertBankAcntDao corMertBankAcntDao;
    private IExcelService excelService;

    @Override
    public IPage<CorMertBankAcnt> findCorMertBankAcntByPage(QueryPage queryPage) {
        logger.info("entering action::CorMertBankAcntBizImpl.findCorMertBankAcntByPage()...");
        return corMertBankAcntDao.findCorMertBankAcntByPage(queryPage);
    }

    @Override
    public CorMertBankAcnt getCorMertBankAcntById(String id) {
        logger.info("entering action::CorMertBankAcntBizImpl.getCorMertBankAcntById()...");
        return corMertBankAcntDao.load(id);
    }

    @Override
    public CorMertBankAcnt saveCorMertBankAcnt(CorMertBankAcnt corMertBankAcnt) {
        logger.info("entering action::CorMertBankAcntBizImpl.saveCorMertBankAcnt()...");
        corMertBankAcnt.setId(null);
        corMertBankAcntDao.saveOrUpdate(corMertBankAcnt);
        return corMertBankAcnt;
    }

    @Override
    public CorMertBankAcnt updateCorMertBankAcnt(CorMertBankAcnt corMertBankAcnt) {
        logger.info("entering action::CorMertBankAcntBizImpl.updateCorMertBankAcnt()...");
        corMertBankAcntDao.saveOrUpdate(corMertBankAcnt);
        return corMertBankAcnt;
    }

    @Override
    public void exportCorMertBankAcnt(ExportSetting exportSetting) {
        logger.info("entering action::CorMertBankAcntBizImpl.exportCorMertBankAcnt()...");
        exportSetting.setPageSize(99999999);
        exportSetting.setPageIndex(0);
        Page<CorMertBankAcnt> corMertBankAcntPage = (Page<CorMertBankAcnt>) corMertBankAcntDao
                .findCorMertBankAcntByPage(exportSetting);

        excelService.exportToFile(corMertBankAcntPage.getRows(), exportSetting);
    }

    public CorMertBankAcnt find(String merchantCode) {

        return corMertBankAcntDao.findCorMertBankAcnt(merchantCode);
    }
    @Override
    public CorMertBankAcnt findCorMertBankAcntInfo(CorMertBankAcnt corMertBankAcnt){
        return corMertBankAcntDao.findCorMertBankAcntInfo(corMertBankAcnt);
    }
    public ICorMertBankAcntDao getCorMertBankAcntDao() {
        logger.info("entering action::CorMertBankAcntBizImpl.getCorMertBankAcntDao()...");
        return corMertBankAcntDao;
    }

    public void setCorMertBankAcntDao(ICorMertBankAcntDao corMertBankAcntDao) {
        logger.info("entering action::CorMertBankAcntBizImpl.setCorMertBankAcntDao()...");
        this.corMertBankAcntDao = corMertBankAcntDao;
    }

    public IExcelService getExcelService() {
        logger.info("entering action::CorMertBankAcntBizImpl.getExcelService()...");
        return excelService;
    }

    public void setExcelService(IExcelService excelService) {
        logger.info("entering action::CorMertBankAcntBizImpl.setExcelService()...");
        this.excelService = excelService;
    }

}

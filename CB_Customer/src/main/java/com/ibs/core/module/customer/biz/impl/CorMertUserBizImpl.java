package com.ibs.core.module.customer.biz.impl;

import java.util.Date;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.customer.biz.ICorMertUserBiz;
import com.ibs.core.module.customer.dao.ICorMertUserDao;
import com.ibs.core.module.customer.dao.ICorMertUserLogDao;
import com.ibs.core.module.customer.domain.CorMertUser;
import com.ibs.core.module.customer.domain.CorMertUserLog;
import com.ibs.core.module.customer.dto.CorMertUserDto;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.BeanUtils;

public class CorMertUserBizImpl extends BaseBiz implements ICorMertUserBiz {

    private ICorMertUserDao corMertUserDao;
    private IExcelService excelService;
    private ICorMertUserLogDao corMertUserLogDao;
    @Override
    public IPage<CorMertUserDto> findCorMertUserByPage(QueryPage queryPage, CorMertUserDto corMertUserDto) {
        logger.info("entering action::CorMertUserBizImpl.findCorMertUserByPage()...");
        return corMertUserDao.findCorMertUserByPage(queryPage, corMertUserDto);
    }

    @Override
    public CorMertUser getCorMertUserById(String id) {
        logger.info("entering action::CorMertUserBizImpl.getCorMertUserById()...");
        return corMertUserDao.load(id);
    }

    @Override
    public CorMertUser saveCorMertUser(CorMertUser corMertUser) {
        logger.info("entering action::CorMertUserBizImpl.saveCorMertUser()...");
        corMertUser.setId(null);
        corMertUserDao.saveOrUpdate(corMertUser);
        return corMertUser;
    }

    @Override
    public CorMertUser updateCorMertUser(CorMertUser corMertUser) {
        logger.info("entering action::CorMertUserBizImpl.updateCorMertUser()...");
        corMertUserDao.saveOrUpdate(corMertUser);
        CorMertUserLog newCorMertUserLog = new CorMertUserLog();
        BeanUtils.copyObjectTypeProperties(newCorMertUserLog, corMertUser);
        newCorMertUserLog.setId(null);
        newCorMertUserLog.setSourceId(corMertUser.getId());
        newCorMertUserLog.setCreateTime(new Date());
        newCorMertUserLog.setCreator(corMertUser.getUpdator());
        newCorMertUserLog.setUpdateTime(new Date());
        corMertUserLogDao.saveOrUpdate(newCorMertUserLog);
        return corMertUser;
    }

    @Override
    public CorMertUser getUserByUserCode(String userCode) {
        return corMertUserDao.getUserByUserCode(userCode);
    }
    public ICorMertUserDao getCorMertUserDao() {
        return corMertUserDao;
    }

    public void setCorMertUserDao(ICorMertUserDao corMertUserDao) {
        this.corMertUserDao = corMertUserDao;
    }

    public IExcelService getExcelService() {
        return excelService;
    }

    public void setExcelService(IExcelService excelService) {
        this.excelService = excelService;
    }

    public ICorMertUserLogDao getCorMertUserLogDao() {
        return corMertUserLogDao;
    }

    public void setCorMertUserLogDao(ICorMertUserLogDao corMertUserLogDao) {
        this.corMertUserLogDao = corMertUserLogDao;
    }

}

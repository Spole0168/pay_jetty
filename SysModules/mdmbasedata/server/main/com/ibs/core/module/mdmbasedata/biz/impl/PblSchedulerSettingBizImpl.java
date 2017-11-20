/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
package com.ibs.core.module.mdmbasedata.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.mdmbasedata.biz.IPblSchedulerSettingBiz;
import com.ibs.core.module.mdmbasedata.dao.IPblSchedulerSettingDao;
import com.ibs.core.module.mdmbasedata.domain.PblSchedulerSetting;
import com.ibs.portal.framework.server.biz.WebBaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_PBL_SCHEDULER_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
public class PblSchedulerSettingBizImpl extends WebBaseBiz implements IPblSchedulerSettingBiz {

	private IPblSchedulerSettingDao pblSchedulerSettingDao;
	private IExcelService excelService;
	
	@Override
	public IPage<PblSchedulerSetting> findPblSchedulerSettingByPage(QueryPage queryPage) {
		logger.info("entering action::PblSchedulerSettingBizImpl.findPblSchedulerSettingByPage()...");
		return pblSchedulerSettingDao.findPblSchedulerSettingByPage(queryPage);
	}

	@Override
	public PblSchedulerSetting getPblSchedulerSettingById(String id) {
		logger.info("entering action::PblSchedulerSettingBizImpl.getPblSchedulerSettingById()...");
		return pblSchedulerSettingDao.load(id);
	}

	@Override
	public PblSchedulerSetting savePblSchedulerSetting(PblSchedulerSetting pblSchedulerSetting) {
		logger.info("entering action::PblSchedulerSettingBizImpl.savePblSchedulerSetting()...");
		pblSchedulerSetting.setId(null);
		pblSchedulerSettingDao.saveOrUpdate(pblSchedulerSetting);
		return pblSchedulerSetting;
	}

	@Override
	public PblSchedulerSetting updatePblSchedulerSetting(PblSchedulerSetting pblSchedulerSetting) {
		logger.info("entering action::PblSchedulerSettingBizImpl.updatePblSchedulerSetting()...");
		pblSchedulerSettingDao.saveOrUpdate(pblSchedulerSetting);
		return pblSchedulerSetting;
	}

	@Override
	public void exportPblSchedulerSetting(ExportSetting exportSetting) {
		logger.info("entering action::PblSchedulerSettingBizImpl.exportPblSchedulerSetting()...");
		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<PblSchedulerSetting> pblSchedulerSettingPage = (Page<PblSchedulerSetting>) pblSchedulerSettingDao
				.findPblSchedulerSettingByPage(exportSetting);

		excelService.exportToFile(pblSchedulerSettingPage.getRows(), exportSetting);
	}

	public IPblSchedulerSettingDao getPblSchedulerSettingDao() {
		logger.info("entering action::PblSchedulerSettingBizImpl.getPblSchedulerSettingDao()...");
		return pblSchedulerSettingDao;
	}

	public void setPblSchedulerSettingDao(IPblSchedulerSettingDao pblSchedulerSettingDao) {
		logger.info("entering action::PblSchedulerSettingBizImpl.setPblSchedulerSettingDao()...");
		this.pblSchedulerSettingDao = pblSchedulerSettingDao;
	}

	public IExcelService getExcelService() {
		logger.info("entering action::PblSchedulerSettingBizImpl.getExcelService()...");
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		logger.info("entering action::PblSchedulerSettingBizImpl.setExcelService()...");
		this.excelService = excelService;
	}

}

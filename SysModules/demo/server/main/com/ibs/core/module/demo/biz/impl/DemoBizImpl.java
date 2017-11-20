package com.ibs.core.module.demo.biz.impl;

import com.ibs.common.module.frameworkimpl.service.IExcelService;
import com.ibs.core.module.demo.biz.IDemoBiz;
import com.ibs.core.module.demo.dao.IDemoDao;
import com.ibs.core.module.demo.domain.Demo;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.ExportSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.Page;
import com.ibs.portal.framework.server.metadata.QueryPage;

/**
 * @author XXX
 * @date   YYYY-MM-DD
 * @version 1.0
 * @description This is action description
 */
public class DemoBizImpl extends BaseBiz implements IDemoBiz {

	/**
	 * demo dao interface
	 */
	private IDemoDao demoDao;
	
	/**
	 * excel service
	 */
	private IExcelService excelService;
	
	@Override
	public IPage<Demo> findDemoByPage(QueryPage queryPage) {
		logger.info("enter findDemoByPage");
		
		IPage<Demo> demoPage = demoDao.findDemoByPage(queryPage);
		
		logger.info("exit findDemoByPage");
		return demoPage;
	}

	@Override
	public Demo getDemoById(String id) {
		logger.info("enter getDemoById");

		logger.info("get demo by id:" + id);
		Demo demo = demoDao.load(id);
		
		logger.info("exit getDemoById");
		return demo;
	}

	@Override
	public Demo saveDemo(Demo demo) {
		logger.info("enter saveDemo");
		
		demo.setId(null);
		demoDao.saveOrUpdate(demo);

		logger.info("exit saveDemo");
		return demo;
	}

	@Override
	public Demo updateDemo(Demo demo) {
		logger.info("enter updateDemo");
		
		demoDao.saveOrUpdate(demo);

		logger.info("exit updateDemo");
		return demo;
	}

	@Override
	public void exportDemo(ExportSetting exportSetting) {
		logger.info("enter exportDemo");

		exportSetting.setPageSize(99999999);
		exportSetting.setPageIndex(0);
		Page<Demo> demoPage = (Page<Demo>) demoDao
				.findDemoByPage(exportSetting);

		excelService.exportToFile(demoPage.getRows(), exportSetting);
		logger.info("exit exportDemo");
	}

	public IDemoDao getDemoDao() {
		return demoDao;
	}

	public void setDemoDao(IDemoDao demoDao) {
		this.demoDao = demoDao;
	}

	public IExcelService getExcelService() {
		return excelService;
	}

	public void setExcelService(IExcelService excelService) {
		this.excelService = excelService;
	}

}

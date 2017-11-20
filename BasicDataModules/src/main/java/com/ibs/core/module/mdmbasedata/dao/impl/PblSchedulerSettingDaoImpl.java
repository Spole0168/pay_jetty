/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */
/***********************************************************/
package com.ibs.core.module.mdmbasedata.dao.impl;

import com.ibs.core.module.mdmbasedata.dao.IPblSchedulerSettingDao;
import com.ibs.core.module.mdmbasedata.domain.PblSchedulerSetting;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_PBL_SCHEDULER_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
public class PblSchedulerSettingDaoImpl extends BaseEntityDao<PblSchedulerSetting> implements
		IPblSchedulerSettingDao {

	public IPage<PblSchedulerSetting> findPblSchedulerSettingByPage(QueryPage queryPage) {
		logger.info("entering action::PblSchedulerSettingDaoImpl.findPblSchedulerSettingByPage()...");
		return super.findPageBy(queryPage);
	}

	public PblSchedulerSetting load(String id) {
		logger.info("entering action::PblSchedulerSettingDaoImpl.load()...");
		return super.load(id);
	}

	public void saveOrUpdate(PblSchedulerSetting pblSchedulerSetting) {
		logger.info("entering action::PblSchedulerSettingDaoImpl.saveOrUpdate()...");
		super.saveOrUpdate(pblSchedulerSetting);
	}

}

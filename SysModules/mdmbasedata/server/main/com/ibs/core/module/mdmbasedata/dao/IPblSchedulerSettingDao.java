/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

package com.ibs.core.module.mdmbasedata.dao;

import com.ibs.core.module.mdmbasedata.domain.PblSchedulerSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_PBL_SCHEDULER_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
public interface IPblSchedulerSettingDao {

	public IPage<PblSchedulerSetting> findPblSchedulerSettingByPage(QueryPage queryPage);
	
	public void saveOrUpdate(PblSchedulerSetting pblSchedulerSetting);

	public PblSchedulerSetting load(String id);
	
}

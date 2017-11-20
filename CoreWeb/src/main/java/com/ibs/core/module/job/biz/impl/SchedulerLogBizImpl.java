package com.ibs.core.module.job.biz.impl;

import com.ibs.common.module.frameworkimpl.scheduler.dao.IScheduleJobDao;
import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerLog;
import com.ibs.core.module.job.biz.ISchedulerSettingLogBiz;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public class SchedulerLogBizImpl extends BaseBiz implements
		ISchedulerSettingLogBiz {
	private IScheduleJobDao schedulerSettingLogDao;
	public IScheduleJobDao getSchedulerSettingLogDao() {
		return schedulerSettingLogDao;
	}
	public void setSchedulerSettingLogDao(IScheduleJobDao schedulerSettingLogDao) {
		this.schedulerSettingLogDao = schedulerSettingLogDao;
	}
	public IPage<SchedulerLog> findScheduleInfos(QueryPage queryPage) {
		logger.trace("entering biz...");
		
		return this.schedulerSettingLogDao.findScheduleInfos(queryPage);
	}

}

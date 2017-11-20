package com.ibs.core.module.job.biz;

import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerLog;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

public interface ISchedulerSettingLogBiz {
	public IPage<SchedulerLog> findScheduleInfos(QueryPage queryPage) ;
}

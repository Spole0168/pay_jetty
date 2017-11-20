package com.ibs.core.module.job.dao;


import java.io.Serializable;
import java.util.List;
import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;


public interface ISchedulerSetDao
{
		/**
		 * 添加
		 * @param scheduleInfo
		 * @return
		 */
		public Serializable saveScheduleSet(SchedulerSetting schedulerSetting);
		/**
		 * 更新
		 * @param scheduleInfo
		 */
		public void updateScheduleSet(SchedulerSetting schedulerSet);
		
		/**
		 * 根据名字查询定时任务设置信息
		 */
		public List<SchedulerSetting> findSchedulerSetByName(String name);
		
		/**
		 * 根据定时任务ID查询
		 * @param id
		 * @return
		 */
		public SchedulerSetting load(String id);
		
		public SchedulerSetting findScheduleSetById(String id);
		
		public List<SchedulerSetting> list();
		
		public void saveOrUpdate(SchedulerSetting schSet);
		
		public IPage<SchedulerSetting> findSchedulerByPage(QueryPage queryPage);

}

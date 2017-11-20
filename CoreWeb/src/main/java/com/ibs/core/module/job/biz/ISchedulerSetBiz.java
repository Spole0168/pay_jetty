package com.ibs.core.module.job.biz;


import java.io.Serializable;
import java.util.List;

import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerSetting;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;

/**
 * 
 *  定时任务设置信息操作Biz.
 *
 */
public interface ISchedulerSetBiz
{
	/**
	 * 保存定时任务设置信息对象.
	 * @param schedulerSetting 定时器设置信息对象.
	 * @return 返回ID编号.
	 */
	public Serializable saveScheduleSetting(SchedulerSetting schedulerSetting);
	/**
	 * 更新定时任务设置信息对象.
	 * @param scheduleInfo 定时器设置信息对象.
	 */
	public void updateScheduleSetting(SchedulerSetting schedulerSet);
	 
	/**
	 * 根据名字查询定时任务设置信息对象列表.
	 * @param name 
	 * @return 返回定时任务设置信息对象列表.
	 */
	public List<SchedulerSetting> findSchedulerSetByName(String name);
	
	/**
	 * 根据定时任务ID加载定时任务设置信息对象.
	 * @param id
	 * @return 返回定时任务设置信息对象.
	 */
	public SchedulerSetting load(String id);
	/**
	 * 根据定时任务ID加载定时任务设置信息对象.
	 * @param id
	 * @return 返回定时任务设置信息对象.
	 */
	public SchedulerSetting findScheduleSetById(String id);
	/**
	 * 获取所有定时任务设置信息对象列表.
	 * @return 返回所有定时任务设置信息对象列表.
	 */
	public List<SchedulerSetting> list();
	/**
	 * 保存或更新定时任务设置信息对象.
	 * @param scheduleInfo 定时器设置信息对象.
	 */
	public void saveOrUpdate(SchedulerSetting schSet);
	/**
	 * 根据QueryPage查询条件得到IPage分页对象.
	 * @param queryPage 查询条件.
	 * @return 返回分页对象.
	 */
	public IPage<SchedulerSetting> findSchedulerByPage(QueryPage queryPage);
	
	public void stopJob(String schedulerId);
	/**
	 * 启动schedulerId的Job任务,更新数据库enable字段值为true(启用状态).
	 * @param schedulerId
	 */
	public void startJob(String schedulerId);
	
	public boolean isBlocked(String schedulerId);

}

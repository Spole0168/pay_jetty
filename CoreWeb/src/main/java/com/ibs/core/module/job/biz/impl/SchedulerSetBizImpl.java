package com.ibs.core.module.job.biz.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.BeanFactory;

import com.ibs.common.module.frameworkimpl.scheduler.dao.ISchedulerSettingDao;
import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerSetting;
import com.ibs.common.module.frameworkimpl.scheduler.factory.InitSchedulerFactoryBean;
import com.ibs.core.module.job.biz.ISchedulerSetBiz;
import com.ibs.portal.framework.server.biz.BaseBiz;
import com.ibs.portal.framework.server.context.BeanHolder;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.portal.framework.util.StringUtils;

public class SchedulerSetBizImpl extends BaseBiz implements ISchedulerSetBiz
{
	 
	private ISchedulerSettingDao schedulerSettingDao;
	 
	public ISchedulerSettingDao getSchedulerSettingDao() {
		return schedulerSettingDao;
	}

	public void setSchedulerSettingDao(ISchedulerSettingDao schedulerSettingDao) {
		this.schedulerSettingDao = schedulerSettingDao;
	}

	public SchedulerSetting findScheduleSetById(String id)
	{
		logger.trace("entering biz...");
		return this.schedulerSettingDao.findScheduleSettingById(id);
	}

	public IPage<SchedulerSetting> findSchedulerByPage(QueryPage queryPage)
	{
		String schedulerWar = this.getSchedulerFactoryBean().getGroup();
		//queryPage.addLikeSearch("schedulerWar", trim(schedulerWar));
		//queryPage.addQueryCondition("schedulerWar", "%" + trim(schedulerWar) + "%");
		IPage<SchedulerSetting> result = schedulerSettingDao.findSchedulerByPage(queryPage);
		Collection<SchedulerSetting> list = result.getRows();
		//显示到页面的列表数据与beans.xml文件中的job定义列表取交集.
		for (Iterator<SchedulerSetting> it = list.iterator();it.hasNext();) {
			SchedulerSetting e = it.next();
			try {
			
				e.setCurrentStatus(InitSchedulerFactoryBean.getTriggerStateName(this.getSchedulerFactoryBean().getJobState(e.getSchedulerId())));
				//System.out.println(e.getCurrentStatus());
			} catch (SchedulerException se) {
				logger.error(se.getMessage());
			} catch (RuntimeException re) {
				logger.warn("获取任务["+ e.getSchedulerId() +"]状态失败:" + re.getMessage());
			}
		}
		return result;
		 
	}
	private static String trim(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		return str.trim();
	}

	 

	public List<SchedulerSetting> list()
	{
		logger.trace("entering biz...");
		return schedulerSettingDao.list();
	}

	public void saveOrUpdate(SchedulerSetting schSet)
	{
		schedulerSettingDao.saveOrUpdate(schSet);
	}

	public Serializable saveScheduleSetting(SchedulerSetting schedulerSetting)
	{
		logger.trace("entering biz...");
		return schedulerSettingDao.saveScheduleSetting(schedulerSetting);
	}

	 

	public SchedulerSetting load(String id)
	{
		logger.trace("entering biz...");
	
		return this.schedulerSettingDao.findScheduleSettingById(id);
	}


	public void startJob(String schedulerId) {
		logger.trace("entering biz...");
		
		SchedulerSetting setting = this.schedulerSettingDao.findScheduleSettingById(schedulerId);
		setting.setEnable(true);
		this.schedulerSettingDao.updateScheduleSetting(setting);
		
		this.getSchedulerFactoryBean().startJob(schedulerId);
	}

	public void stopJob(String schedulerId) {
		logger.trace("entering biz...");
		
		this.getSchedulerFactoryBean().stopJob(schedulerId);
		
		SchedulerSetting setting = this.schedulerSettingDao.findSchedulerSettingBySchedulerId(schedulerId);
		setting.setEnable(false);
		this.schedulerSettingDao.updateScheduleSetting(setting);
	}
	
	public boolean isBlocked(String schedulerId) {
		logger.trace("entering biz...");
		try {
			return (this.getSchedulerFactoryBean().getJobState(schedulerId) == Trigger.STATE_BLOCKED);
		} catch (Exception e) {
			 logger.error(e.getMessage(),e);
		}
		return false;
	}
	 
	private InitSchedulerFactoryBean getSchedulerFactoryBean() {
		return (InitSchedulerFactoryBean)BeanHolder.getBean(BeanFactory.FACTORY_BEAN_PREFIX + InitSchedulerFactoryBean.QUARTZ_SCHEDULER_BEAN_NAME);
	}

	public List<SchedulerSetting> findSchedulerSetByName(String name) {
		 
		logger.trace("entering biz...");
		
		return this.schedulerSettingDao.findSchedulerSettingByName(name);
		
		 
	}

	public void updateScheduleSetting(SchedulerSetting schedulerSet) {
		schedulerSettingDao.updateScheduleSetting(schedulerSet);
		
	}
	
}

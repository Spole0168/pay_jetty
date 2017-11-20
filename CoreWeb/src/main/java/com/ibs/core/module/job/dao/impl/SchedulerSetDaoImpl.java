package com.ibs.core.module.job.dao.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ibs.core.module.job.dao.ISchedulerSetDao;
import com.ibs.portal.framework.server.dao.hibernate.BaseEntityDao;
import com.ibs.portal.framework.server.metadata.ColumnValue;
import com.ibs.portal.framework.server.metadata.IPage;
import com.ibs.portal.framework.server.metadata.QueryPage;
import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerSetting;

@Deprecated
public class SchedulerSetDaoImpl extends BaseEntityDao<SchedulerSetting> implements ISchedulerSetDao
{

	@Deprecated
	public SchedulerSetting findScheduleSetById(String id)
	{
		logger.trace("entering dao...");
		return null;
	}

	public IPage<SchedulerSetting> findSchedulerByPage(QueryPage queryPage)
	{
		StringBuilder hql = new StringBuilder(" from SchedulerSet t where 1 = 1 ");

		List<Object> queryList = new ArrayList<Object>();
		for (ColumnValue cv : queryPage.getQueryConditionList()) {
			if (cv.isNotNullValue()) {
 
				if ("id".equals(cv.getColumnName())) {
					hql.append(" and t.id  = ? ");
				}

				if ("name".equals(cv.getColumnName())) {
					hql.append(" and t.name like ? ");
				}
		
				if ("description".equals(cv.getColumnName())) {
					hql.append(" and t.description like ? ");
				}

				if ("param1".equals(cv.getColumnName())) {
					hql.append(" and t.param1 =  ? ");
				}

				if ("param2".equals(cv.getColumnName())) {
					hql.append(" and t.param2 = ? ");
				}
				queryList.add(cv.getValue());
			}
			}
		queryPage.setHqlString(hql.toString());
		  
		return this.findPage(hql.toString(),(List<Object>)new ArrayList<Object>(),queryPage.getPageSize(),queryPage.getPageIndex());
	}

	@Deprecated
	public List<SchedulerSetting> findSchedulerSetByName(String name)
	{
		logger.trace("entering dao...");
		return null;
	}
	@Deprecated
	public Serializable saveScheduleSet(SchedulerSetting schedulerSet)
	{
		logger.trace("entering dao...");
		return null;
	}

	public void updateScheduleSet(SchedulerSetting schedulerSet)
	{logger.trace("entering dao...");
		super.update(schedulerSet);

	}
	
	public List<SchedulerSetting> list()
	{logger.trace("entering dao...");
		StringBuffer hql = new StringBuffer();

		return super.find(hql.toString());
	}
	
	 public void saveOrUpdate(SchedulerSetting schSet)
	    {logger.trace("entering dao...");
	    	super.saveOrUpdate(schSet);
	    }

	public SchedulerSetting load(String id)
	{logger.trace("entering dao...");
		return super.load(id);
	}
}

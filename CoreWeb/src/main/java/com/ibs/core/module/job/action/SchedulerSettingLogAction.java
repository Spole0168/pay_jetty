package com.ibs.core.module.job.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerLog;
import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerSetting;
import com.ibs.core.module.job.biz.ISchedulerSettingLogBiz;
import com.ibs.portal.framework.server.action.BaseAction;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;

/**
 * 
 *  当时任务日志表操作Action.
 *
 */
public class SchedulerSettingLogAction extends CrudBaseAction {

	 
	private static final long serialVersionUID = 3775166894485640680L;
	private ISchedulerSettingLogBiz schedulerSettingLogBiz;
	private String schedulerId;

	public String getSchedulerId() {
		return schedulerId;
	}

	public void setSchedulerId(String schedulerId) {
		this.schedulerId = schedulerId;
	}

	public ISchedulerSettingLogBiz getSchedulerSettingLogBiz() {
		return schedulerSettingLogBiz;
	}

	public void setSchedulerSettingLogBiz(
			ISchedulerSettingLogBiz schedulerSettingLogBiz) {
		this.schedulerSettingLogBiz = schedulerSettingLogBiz;
	}
	@Override
	public String create() {
		logger.trace("entering action...");
		throw new UnsupportedOperationException("不支持此操作!");
	}
	@Override
	public String delete() {
		logger.trace("entering action...");
			throw new UnsupportedOperationException("不支持此操作!");
	}

	@Override
	public String export() {
		logger.trace("entering action...");
			throw new UnsupportedOperationException("不支持此操作!");
	}

	@Override
	public String list() {
		logger.trace("entering action...");
		this.setSchedulerId(super.request.getParameter("schedulerId"));
		 System.out.println("123444444");
		return SUCCESS;
	}

	@Override
	public String modify() {
		logger.trace("entering action...");
			throw new UnsupportedOperationException("不支持此操作!");
	}

	@Override
	public String saveOrUpdate() {
		logger.trace("entering action...");
		throw new UnsupportedOperationException("不支持此操作!");
	}

	@Override
	public String search() {
		logger.trace("entering action...");
		
		queryPage.setCalCount(false);
		
		this.queryPage.addEqualSearch("schedulerId", request.getParameter("schedulerId"));
		Page<SchedulerLog> result = (Page<SchedulerLog>)this.schedulerSettingLogBiz.findScheduleInfos(queryPage);
		this.setResult(result); 
		return AJAX_RETURN_TYPE;
	}
	private Page<SchedulerLog> result(){
		List<SchedulerLog> l = new ArrayList<SchedulerLog>();
		SchedulerLog s = new SchedulerLog();
		s.setId("123");
		s.setSchedulerId("aaaaaaaaa");
		l.add(s);
		Page<SchedulerLog> result = new Page<SchedulerLog>(l,1,1,1);
		return result;
	}
	public String abcd() {
		System.out.println("entering abcd method!!!");
		return SUCCESS;
	}
	//private Map<String,Object> result2 = new HashMap<String,Object>();
	private Page<?> result2;
	
	
	 

	public <T> Page<T> getResult2() {
		return (Page<T>)result2;
	}

	public <T>void setResult2(Page<T> result2) {
		this.result2 = result2;
	}
	private Map<String,Object> result3 = new HashMap<String,Object>();
	
	public Map<String, Object> getResult3() {
		return result3;
	}

	public void setResult3(Map<String, Object> result3) {
		this.result3 = result3;
	}

	public String abcdSearch() {
		List<SchedulerSetting> list = new ArrayList<SchedulerSetting>();
		SchedulerSetting ss = new SchedulerSetting();
		SchedulerSetting ss2 = new SchedulerSetting();
		ss2.setId("2");
		ss2.setName("lucy");
		ss.setId("1");
		ss.setName("kitty");
		list.add(ss);
		list.add(ss2);
		this.result3.put("rows", list);
		Page<SchedulerSetting> p = new Page<SchedulerSetting>(list,list.size(),list.size(),1);
		
		//this.setResult2(p);
		//this.setResult(p);
		System.out.println("entering abcdSearch method!!!");
		return BaseAction.AJAX_RETURN_TYPE;
	}
	 



}

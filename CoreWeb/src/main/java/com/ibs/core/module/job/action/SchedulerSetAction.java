package com.ibs.core.module.job.action;

import org.quartz.CronExpression;

import com.ibs.common.module.frameworkimpl.scheduler.domain.SchedulerSetting;
import com.ibs.core.module.job.biz.ISchedulerSetBiz;
import com.ibs.portal.framework.server.action.CrudBaseAction;
import com.ibs.portal.framework.server.metadata.Page;

public class SchedulerSetAction extends CrudBaseAction {
	public static final int START = 1;
	public static final int STOP = 0;
	private static final long serialVersionUID = 110L;
	private ISchedulerSetBiz schedulerSetBiz;
	private String id;
	private SchedulerSetting schedulerSet = new SchedulerSetting();
	private static int status = STOP; 
	private String returnMsg;
	
	
	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	/**
	 * 任务阻塞,处于运行中状态.
	 */
	private static final String STATE_BLOCKED = "4";
	
	//private InitSchedulerFactoryBean schedulerFactoryBean;
	
	private String blocked;
	
	 
	 
	 

	public String getBlocked() {
		return blocked;
	}

	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}

	 

	public   int getStatus() {
		return status;
	}

	public   void setStatus(int status) {
		SchedulerSetAction.status = status;
		
	}

	 
	public SchedulerSetting getSchedulerSet() {
		return schedulerSet;
	}

	public void setSchedulerSet(SchedulerSetting schedulerSet) {
		this.schedulerSet = schedulerSet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ISchedulerSetBiz getSchedulerSetBiz() {
		return schedulerSetBiz;
	}

	public void setSchedulerSetBiz(ISchedulerSetBiz schedulerSetBiz) {
		this.schedulerSetBiz = schedulerSetBiz;
	}

	@Override
	public String create() {
		logger.trace("entering action...");
		return null;
	}

	@Override
	public String delete() {
		logger.trace("entering action...");
		return null;
	}

	@Override
	public String export() {
		logger.trace("entering action...");
		return null;
	}

	@Override
	public String list() {
		logger.trace("entering list...");	 
		 
		this.setBlocked(super.request.getParameter("blocked"));
		
		//triggerStateList = OptionObjectPairAdapter.getOptionObjectPairList(TriggerType.class);
		return SUCCESS;
	}

	@Override
	public String modify() {
		logger.trace("entering action...");
		//this.schedulerFactoryBean.stopJob(super.request.getParameter("schedulerId"));
		//this.setSchedulerSet(schedulerSetBiz.load(id));
		this.setSchedulerSet(schedulerSetBiz.findScheduleSetById(id));
		
		
		return SUCCESS;
	}

	@Override
	public String saveOrUpdate() {
		logger.trace("entering action...");
		this.schedulerSetBiz.saveOrUpdate(schedulerSet);
		return SUCCESS;
	}
	 
	public String check(){
		logger.info("check");
		String cron = request.getParameter("cron");
		logger.info("cron:" + cron);
		if(null != cron){
			if(CronExpression.isValidExpression(cron)){
				returnMsg = "01";
			} else {
				returnMsg = "02";
			}
		}
		return AJAX_RETURN_TYPE;
	}
	 
	@Override
	public String search() {
		
		 
		logger.trace("entering search...");	 
		
		queryPage.setCalCount(false);

		Page<SchedulerSetting> result = (Page<SchedulerSetting>) schedulerSetBiz
				.findSchedulerByPage(queryPage);
 
		if (logger.isDebugEnabled()) {
			logger.debug("Result count = " + result.getRecords());
		}
 
		setResult(result);
		return AJAX_RETURN_TYPE;

	}

	public String start() {
		logger.trace("entering action...");
		this.schedulerSetBiz.startJob(super.request.getParameter("schedulerId"));
		return SUCCESS;
	}

	public String stop() {
		logger.trace("entering action...");
		String schedulerId = super.request.getParameter("schedulerId");
		if(this.schedulerSetBiz.isBlocked(schedulerId)) {
			this.setBlocked(STATE_BLOCKED);
			
			return SUCCESS;
		}
		this.schedulerSetBiz.stopJob(schedulerId);
		return SUCCESS;
	}  
	
	 
}

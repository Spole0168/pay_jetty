package com.ibs.core.module.mdmbasedata.domain;
/*
 * IBS Payment project
 * Copyright IBS 2016-2020
 */

import java.util.Date;
import com.ibs.portal.framework.server.domain.BaseEntity;

/*
 * @created by	: sicon
 * @version 	: 1.0
 * @comments	: code generated based on database T_PBL_SCHEDULER_SETTING
 * @modify		: your comments goes here (author,date,reason).
 */
public class PblSchedulerSetting extends BaseEntity {
	
	private String id; // ID
	private String schedulerId; // SCHEDULER_ID
	private String schedulerWar; // SCHEDULER_WAR
	private String name; // NAME
	private String description; // DESCRIPTION
	private String type; // TYPE
	private String enable; // ENABLE
	private String cronExpress; // CRON_EXPRESS
	private Date simpleStartTime; // SIMPLE_START_TIME
	private Date simpleEndTime; // SIMPLE_END_TIME
	private Integer simpleRepeatCount; // SIMPLE_REPEAT_COUNT
	private Integer simpleRepeatIntervel; // SIMPLE_REPEAT_INTERVEL
	private String param1; // PARAM1
	private String param2; // PARAM2
	private String refValue; // REF_VALUE
	private String remark; // REMARK
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getSchedulerId(){
		return schedulerId;
	}
	public void setSchedulerId(String schedulerId){
		this.schedulerId = schedulerId;
	}

	public String getSchedulerWar(){
		return schedulerWar;
	}
	public void setSchedulerWar(String schedulerWar){
		this.schedulerWar = schedulerWar;
	}

	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}

	public String getDescription(){
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}

	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}

	public String getEnable(){
		return enable;
	}
	public void setEnable(String enable){
		this.enable = enable;
	}

	public String getCronExpress(){
		return cronExpress;
	}
	public void setCronExpress(String cronExpress){
		this.cronExpress = cronExpress;
	}

	public Date getSimpleStartTime(){
		return simpleStartTime;
	}
	public void setSimpleStartTime(Date simpleStartTime){
		this.simpleStartTime = simpleStartTime;
	}

	public Date getSimpleEndTime(){
		return simpleEndTime;
	}
	public void setSimpleEndTime(Date simpleEndTime){
		this.simpleEndTime = simpleEndTime;
	}

	public Integer getSimpleRepeatCount(){
		return simpleRepeatCount;
	}
	public void setSimpleRepeatCount(Integer simpleRepeatCount){
		this.simpleRepeatCount = simpleRepeatCount;
	}

	public Integer getSimpleRepeatIntervel(){
		return simpleRepeatIntervel;
	}
	public void setSimpleRepeatIntervel(Integer simpleRepeatIntervel){
		this.simpleRepeatIntervel = simpleRepeatIntervel;
	}

	public String getParam1(){
		return param1;
	}
	public void setParam1(String param1){
		this.param1 = param1;
	}

	public String getParam2(){
		return param2;
	}
	public void setParam2(String param2){
		this.param2 = param2;
	}

	public String getRefValue(){
		return refValue;
	}
	public void setRefValue(String refValue){
		this.refValue = refValue;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	
}

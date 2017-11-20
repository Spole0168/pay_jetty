package com.ibs.core.module.demo.domain;

import com.ibs.portal.framework.server.domain.BaseEntity;

/**
 * @author XXX
 * @date   YYYY-MM-DD
 * @version 1.0
 * @description This is action description
 */
public class Demo extends BaseEntity {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 5634114314212239630L;
	
	/**
	 * demoe id
	 */
	private String id;
	
	/**
	 * demo name
	 */
	private String demoName;
	
	/**
	 * demo description
	 */
	private String demoDescription;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDemoName() {
		return demoName;
	}
	
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}
	
	public String getDemoDescription() {
		return demoDescription;
	}
	
	public void setDemoDescription(String demoDescription) {
		this.demoDescription = demoDescription;
	}
	
}

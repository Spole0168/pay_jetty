package com.ibs.core.dubbo.dto;

import java.io.Serializable;

/**
 * @author XXX
 * @date   YYYY-MM-DD
 * @version 1.0
 * @description This is action description
 */
public class DemoDto  implements Serializable{

	/**
	 * serial version UID
	 */
	
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

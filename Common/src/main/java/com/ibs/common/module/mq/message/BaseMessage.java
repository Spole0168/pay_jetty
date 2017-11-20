package com.ibs.common.module.mq.message;

import java.util.Date;

/**
 * 通用报文类
 * @author wangxy
 *
 */
public class BaseMessage {
	// 报文内容
	private String content;
    // 报文发送时间
	private Date sendTime ;
	// 报文接收时间
	private Date receiveTime;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	
	
	
}

package com.ibs.common.module.mq.sender;

import com.ibs.common.module.mq.message.BaseMessage;

/**
 * MQ 报文发送接口
 * @author wangxy
 *
 */
public interface IMQSender {
	/**
	 * 发送报文
	 * @param queueName 队列名称
	 * @param message  IBS通用 报文
	 */
	public void sendMessage(String queueName, BaseMessage message);
	
	/**
	 * 发送报文	 *
	 * @param message  IBS通用 报文
	 */
	public void sendMessage(BaseMessage message);

}

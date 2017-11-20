package com.ibs.common.module.mq.receiver;

import com.ibs.common.module.mq.message.BaseMessage;

/**
 * 取得报文接口
 * @author wangxy
 *
 */
public interface IMQReceiver {
	/**
	 * 取得报文
	 * @param queueName  队列名称
	 * @return  IBS通用报文
	 */
	public BaseMessage receiveMessage(String queueName);
}

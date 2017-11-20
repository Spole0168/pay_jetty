package com.ibs.common.module.mq.listener.impl;

import java.util.Date;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.ibs.common.module.mq.listener.IMQListener;
import com.ibs.common.module.mq.message.BaseMessage;

/**
 * 报文监听抽象基类
 * 
 * @author wangxy
 *
 */
public abstract class MQListenerAbstract implements IMQListener {
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * 监听到报文
	 * 
	 * @param Message
	 *            MQ报文内容
	 */
	@Override
	public void onMessage(String content) {
		logger.info("========== onMessage start ==========");
		
		BaseMessage ibsMsg = new BaseMessage();

		logger.info("=== received(listen) message content : " + content);

		ibsMsg.setContent(content);
		ibsMsg.setReceiveTime(new Date());

		receiveMessage(ibsMsg);

		logger.info("========== onMessage end ==========");

	}

	/**
	 * 子类需要实现的方法。
	 * 
	 * @param ibsMsg
	 *            IBS 通用报文
	 */
	protected abstract void receiveMessage(BaseMessage ibsMsg);

}

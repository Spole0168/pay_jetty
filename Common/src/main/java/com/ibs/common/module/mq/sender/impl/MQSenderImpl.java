package com.ibs.common.module.mq.sender.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.ibs.common.module.mq.message.BaseMessage;
import com.ibs.common.module.mq.sender.IMQSender;

/**
 * 发送报文工具类
 * 
 * @author wangxy
 *
 */
public class MQSenderImpl implements IMQSender {

	private Logger logger = Logger.getLogger(this.getClass());
	/** JMS 模板 */
	private JmsTemplate jmsTemplate;
	
	/** 队列名称  */
	private String destination;

	@Override
	/**
	 * 发送报文
	 * 
	 * @param queueName
	 *            队列名称
	 * @param message
	 *            IBS通用 报文
	 */
	public void sendMessage(String queueName, BaseMessage message) {

		logger.info("========== sendMessage start ==========");
		logger.info("=== sending MQ message to queueName : " + queueName);
		logger.info("=== sending message content : " + message.getContent());

		try {

			// 发送报文
			final String content = message.getContent();
			jmsTemplate.send(queueName, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(content);
				}
			});

			logger.info("========== sendMessage end ==========");

		} catch (Exception e) {
			logger.error("##########  Sending MQ Message Failed ！  ##########");
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	@Override
	public void sendMessage(BaseMessage message) {
		
		this.sendMessage(destination, message);
		
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	

}

package com.ibs.common.module.mq.receiver.impl;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import com.ibs.common.module.mq.message.BaseMessage;
import com.ibs.common.module.mq.receiver.IMQReceiver;


/**
 * 接收报文工具类
 * 
 * @author wangxy
 *
 */
public class MQReceiverImpl implements IMQReceiver {

	private Logger logger = Logger.getLogger(this.getClass());
	/** JMS 模板 */
	private JmsTemplate jmsTemplate;

	@Override
	/**
	 * 取得报文
	 * 
	 * @param queueName
	 *            队列名称
	 * @return IBS通用报文
	 */
	public BaseMessage receiveMessage(String queueName) {
		logger.info("========== receiveMessage start ==========");
		logger.info("=== receiving MQ message from queueName : " + queueName);
		

		BaseMessage ibsMsg = new BaseMessage();

		try {

			// 接收报文
//			Message message = jmsTemplate.receive(queueName);
			
			String content = (String)jmsTemplate.receiveAndConvert(queueName);
			
			logger.info("=== received message content : " + content);
			
			ibsMsg.setContent(content);	
			ibsMsg.setReceiveTime(new Date());
			
/*
			if (message instanceof TextMessage) {

				TextMessage textMessage = (TextMessage) message;
				String content = textMessage.getText();
				
				logger.info("=== received message content : " + content);
				
				ibsMsg.setContent(content);				

			} else {
				logger.error(
						"##########  The type of MQ Message is not correct ！ ('TextMessage' is expected) ##########");
			}
*/
		} catch (Exception e) {
			logger.error("##########  Receiving MQ Message Failed ！  ##########");
			logger.error(e.getMessage());
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		logger.info("========== receiveMessage end ==========");
		return ibsMsg;

	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}

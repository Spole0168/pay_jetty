package com.ibs.common.module.mq.listener;

/**
 * 监听报文接口
 * @author wangxy
 *
 */
public interface IMQListener {
    /**
     * 监听到报文
     * @param message MQ报文内容
     */
	public void onMessage(String messageContent);
}

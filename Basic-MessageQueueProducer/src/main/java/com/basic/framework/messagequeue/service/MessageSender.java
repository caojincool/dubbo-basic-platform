package com.basic.framework.messagequeue.service;


/**
 * 
 *
 * @date 2017年7月12日 下午12:09:16
 * 
 * @Description: 向消息队列发送消息
 *
 */
public interface MessageSender {

	/**
	 * 
	 * @date 2017年7月12日 下午12:09:02
	 * 
	 * @Description: 发送消息
	 * @param key
	 * @param queueCode
	 * @param message
	 *
	 */
	public void sendMessage(String key, String queueCode, String message) ;
}
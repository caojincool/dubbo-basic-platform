package com.basic.framework.messagequeue.service;

/**
 * 
 *
 * @date 2017年7月12日 下午3:55:10
 * 
 * @Description: 业务实现类要实现该接口
 *
 */
public interface MessageHandler {
	
	/**
	 * 
	 * @date 2017年7月12日 下午3:55:27
	 * 
	 * @Description: 业务类回调接口
	 * @param message
	 *
	 */
	public void handle(String message);
}

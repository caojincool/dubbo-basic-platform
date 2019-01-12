package com.basic.framework.messagequeue.handler;

import org.springframework.stereotype.Repository;

import com.basic.framework.messagequeue.service.MessageHandler;

/**
 * 
 *
 * @date 2017年7月12日 下午4:08:50
 * 
 * @Description: 测试业务回调类
 *
 */
@Repository
public class TestConsumerListener2Handler implements MessageHandler{


	@Override
	public void handle(String message) {
		System.out.println("OK，我是testConsumerListener2的消息------------------->>>>>>>>"+message);
		
	}
	
}
